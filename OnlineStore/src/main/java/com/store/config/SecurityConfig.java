package com.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
public class SecurityConfig
{
//	@Bean
//	@Primary
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//            .authorizeHttpRequests(auth -> {
//            	auth.requestMatchers("/**").permitAll();
//            	//auth.anyRequest().authenticated();
//            })
//            .build();
//    }
}
