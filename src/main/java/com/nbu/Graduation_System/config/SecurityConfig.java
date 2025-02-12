package com.nbu.Graduation_System.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nbu.Graduation_System.service.user.UserService;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

        private final UserService userService;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userService);
                authProvider.setPasswordEncoder(passwordEncoder());
                return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(authorize -> authorize
                    // Public pages
                    .requestMatchers("/login", "/css/**", "/js/**", "/unauthorized").permitAll()
                    // Student specific pages
                    .requestMatchers("/my-thesis/**").hasRole("STUDENT")
                    // Teacher specific pages
                    .requestMatchers("/thesis-applications/**").hasRole("TEACHER")
                    .requestMatchers("/teachers/**").hasRole("TEACHER")
                    .requestMatchers("/students/**").hasRole("TEACHER")
                    .requestMatchers("/departments/**").hasRole("TEACHER")
                    // Authenticated pages
                    .anyRequest().authenticated())
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll())
                .exceptionHandling(ex -> ex
                    .accessDeniedPage("/unauthorized"));
        
            return http.build();
        }
}