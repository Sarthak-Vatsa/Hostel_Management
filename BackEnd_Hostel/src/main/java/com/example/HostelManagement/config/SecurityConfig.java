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
    @Qualifier("myUserDetailsService") // Handles students
    private UserDetailsService studentDetailsService;

    @Autowired
    @Qualifier("adminDetailsService") // Handles admins
    private UserDetailsService adminDetailsService;

    @Autowired
    private SessionAuthFilter sessionAuthFilter;

//    @Autowired
//    private CustomAuthenticationSuccessHandler studentSuccessHandler;
//
//    @Autowired
//    private AdminAuthenticationSuccessHandler adminSuccessHandler;

//    @Bean
//    public AuthenticationProvider studentAuthProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(studentDetailsService);
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationProvider adminAuthProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(adminDetailsService);
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(studentAuthProvider())
//                .authenticationProvider(adminAuthProvider())
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173")); // React frontend URL
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())// Disable CSRF for simplicity
                .addFilterBefore(sessionAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .securityMatcher("/**") // Ensures Spring Security manages all routes without interfering
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Allow all requests (NO auth control)
//                .logout(logout -> logout
//                        .logoutUrl("/students/logout")  // Ensure this matches your controller method
//                        .logoutSuccessUrl("/students/signin")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                );
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/students/signup", "/students/signin", "/admin/signup", "/admin/signin").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Admin access
//                        .requestMatchers("/students/**").hasRole("STUDENT")  // Student access
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginProcessingUrl("/students/signin")
//                        .usernameParameter("username") //name of the var sent by frontend
//                        .passwordParameter("password")
//                        .successHandler(studentSuccessHandler) // Use custom success handler
//                        .permitAll()
//                )
//                .formLogin(form -> form
//                        .loginProcessingUrl("/admin/signin")
//                        .usernameParameter("email")  // Admin logs in with email
//                        .passwordParameter("password")
//                        .successHandler(adminSuccessHandler)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // URL for triggering logout
//                        .logoutSuccessHandler(customLogoutSuccessHandler())
//                        .invalidateHttpSession(true) // Invalidate the session
//                        .deleteCookies("JSESSIONID") // Delete cookies
//                        .permitAll()
//                )
//                .httpBasic(Customizer.withDefaults()); // Enable basic authentication

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
//        return new CustomLogoutSuccessHandler();
//    }
}
