package com.example.sale3.security;

import com.example.sale3.service.UserAuthen;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
//                .requestMatchers("https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css",
//                        "https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js",
//                        "https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js",
//                        "https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js",
//                        "https://kit.fontawesome.com/a076d05399.js").permitAll()
//                .requestMatchers("/css/**", "/js/**", "/image/**","/login", "/register", "/home").permitAll()
//                .requestMatchers(HttpMethod.GET, "/products/**", "/product/**", "/categories/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/admin", "/category", "/product", "/order-page").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/category", "/product").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/category/**", "/product/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/category/**", "/product/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/home?error=true")
                //login
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/login?error=true")
                .permitAll()
                //logout
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();

        return http.build();
    }
    @Autowired
    private UserAuthen userAuthen;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthen).passwordEncoder(passwordEncoder);
    }

}
