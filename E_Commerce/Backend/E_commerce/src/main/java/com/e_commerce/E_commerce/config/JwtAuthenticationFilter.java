package com.e_commerce.E_commerce.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    private final UserDetailsService userDetailsService;

    //BU GELEN HER İSTEĞİ KARŞILAR
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String autHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (autHeader == null || !autHeader.startsWith("Bearer ")) {
            //Başlık boşsa ve header kısmı bearerla başlamıyorsa Sıradaki filtera geçme ve burada dur.Ondan dolayı hemen altına return koyduk.
            filterChain.doFilter(request,response);
            return;
        }
        //Burada Token Bearer başlığından ayıklanıyor.Boşlukla birlikte 7 karakter sonra tokenımız başladığı için 7.indeksden itibaren olan kısmı al demiş şolduk.
        jwt = autHeader.substring(7);

        userEmail=jwtService.extractUsername(jwt);//email'i tokendan çıkarıyorum.USername olarak isimlendirildi ama springde username maile karşılık geliyor.

        //Ardından çıkan userEmail kontrol edilir ve Kullanıcının sistemde otantike olup olmadığına bakılır.

        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()== null){
            //Email boş değilse ve kişi otantike değilse
            //Buradaki loadUserByUsername için ApplicationConfig oluşturduk ve içinde loadUserByUsername metodunu kendimiz doldurduk.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)){
                //Token geçerliyse
                //UsernamePasswordAuthenticationToken bu metotla kullancıı adı ve şifre doğrulama belirteci nesnesi oluşturduk.
                //Tam olarak detayını öğren bu metodun(UsernamePasswordAuthenticationToken)
                UsernamePasswordAuthenticationToken authtoken =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }

filterChain.doFilter(request,response);


    }
}
