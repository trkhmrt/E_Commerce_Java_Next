package com.e_commerce.E_commerce.config;

import com.e_commerce.E_commerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    //Buranın neden oluşturulduğunu çok daha detaylı araştır.

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        //Kullanıcıyı veritabnına giderek oradan getirecej ve döndürecek
        //Bunun için userRepository oluşturulacak.
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //Kimlik doğrulama sağlayacımız oldu.BU Securityconfig in işine yarayacak.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //DaoAuthenticationProvider'a bilgi almak için hangi kullanıcı ayrıntıları hizmetini kullanacağını söylüyoruz.
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        //Bunu detaylı araştır.
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
