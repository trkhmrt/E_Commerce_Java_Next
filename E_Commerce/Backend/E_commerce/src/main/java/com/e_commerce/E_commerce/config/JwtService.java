package com.e_commerce.E_commerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //Bu class da kişinin emaili ve iddaları ayıklanacak token içerisinden
    //Burada username olarak geçiyor ama biz email kullanıyoruz username yerine.

    private static final String SECRET_KEY = "827DBC74305F3974C90E628336A022CC9E5A88F47CAB677CB5372C782C46C1F1";

    public String extractUsername(String token) {
        //Tek bir idda ayıklama metodunu çağırıp Subject olan iddayı ayıklıyoruz.Çünkü subject bizim kullanıcı adımızdır ve biz kullanıcı adı yerine email kullanıyorduk.
        return extractClaim(token, Claims::getSubject);
    }

    //T  Function<Claims, T> claimsResolver neden kullanılmış
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        //Burada ise bütün iddalar arasından tek bir iddaa ayıklayabiliyoruz.
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        //Daha sonradan ekstra özellikler eklemek istersem kullancağım generateToken
        //Aşağıdakine yani asıl token üretici metoda Map ve userdetails gönderiyor.
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //Burada username alınıyor ancak springde bu her zaman maile karşılık gelir
                .setIssuedAt(new Date(System.currentTimeMillis()))//Ne zaman oluşturuldu.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))//1000 mili saniye * 60 dk *24 saat kadar geçerli
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)//Token'ı imzaladım.
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        //Token valid etmek için extractUsername metoduna token yollanır ve kullanıcı username bilgisi tokendan alınır.
        final String username = extractUsername(token);
        //Girilen kullanıcı adı ile tokendaki kullanıcı adının aynı olup olmadığına bakılır.
        //Yani bir kere giriş yapıldıysa java spring kullanıcı bilgilerini userDetails içiinde tutar.Buradan aldığımız bilgi ile istek atıldığında gelen token içinde saklanan kullanıcı adı aynı mı bakılacak ayrıca tokenın süresinin dolup dolmadığına bakılacak.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        //Expiration date i de tokendan ayıklamamız gerekecek.Bundan dolayı extractExpiration isimli metot oluşturuldu.
        //before birlikte tarihleri kıyasladık.
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        //Username(yani mail)'i ayıkladığımız yöntemle expiration(son geçerlilik tarihi)'da ayıkladık.
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        //Burada import io.jsonwebtoken.Jwts sayesinde tokenı parse edebiliyoruz ve tokenı açabilmek için setSignKey() yani imzalama keyine ihtiyacımız oluyor.
        //Burada tüm iddiaları alabileceğiz.Email Role vs gibi bilgiler bunun içerisinden dönecek.
        //Bunlara iddaa denmesinin sebebi dışarıdan gelen kişiinin kim olduğu belli değildir.Ve içeride kayıtlı olan bir kişi olduğunu iddaa eder.Doğrulandıktan sonra inanılır.

        //Bu metotla birlikte idda kısmının gövdesinin tamamını alıyoruz
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
