package com.example.HostelManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SessionAuthFilter sessionAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173", "" +
                            "https://hostel-management-ashy.vercel.app/")); // React frontend URL
                    //config.setAllowedOrigins(List.of("*")); // for app

                    //config.setAllowedOrigins(List.of("*"));
                    // config.setAllowedOrigins(List.of("*"));
                    config.setAllowCredentials(false);
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*")); //disable for app
                    config.setAllowCredentials(true); //disable for app
                    return config;
                }))
                .csrf(csrf -> csrf.disable())// Disable CSRF for simplicity
                .addFilterBefore(sessionAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .securityMatcher("/**") // Ensures Spring Security manages all routes without interfering
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()); // Allow all requests (NO auth control)


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
