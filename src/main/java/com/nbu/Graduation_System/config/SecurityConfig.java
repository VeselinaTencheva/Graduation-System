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

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authProvider(UserService userService, PasswordEncoder passwordEncoder) {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userService);
            authProvider.setPasswordEncoder(passwordEncoder);
            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(authorize -> authorize
                    // Public pages
                    .requestMatchers("/login", "/css/**", "/js/**", "/unauthorized").permitAll()

                    // API – only ADMIN should access user API
                    .requestMatchers("/api/users/**").hasRole("ADMIN")
                    .requestMatchers("/api/teachers/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/api/students/**").hasAnyRole("TEACHER", "ADMIN")

                    // Student + Teacher pages (also allow ADMIN)
                    .requestMatchers("/my-thesis/**", "/theses/**", "/thesis-reviews/**")
                        .hasAnyRole("STUDENT", "TEACHER", "ADMIN")

                    // Teacher specific pages (also allow ADMIN)
                    .requestMatchers("/thesis-applications/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/thesis-reviews/new/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/thesis-reviews/edit/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/thesis-reviews/delete/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/thesis-defenses/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/defense-sessions/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/teachers/**").hasAnyRole("ADMIN")
                    .requestMatchers("/students/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/departments/**").hasAnyRole("TEACHER", "ADMIN")

                    // Any other request just needs to be authenticated
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                )
                .httpBasic(customizer -> {})   // 🔹 enable HTTP Basic for APIs
                .logout(logout -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll()
                )
                .exceptionHandling(ex -> ex
                    .accessDeniedPage("/unauthorized")
                );

            return http.build();
        }


}