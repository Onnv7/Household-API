package com.onnv.household.security;

import com.onnv.household.security.custom.CustomUserDetailsService;
import com.onnv.household.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.onnv.household.constants.SecurityConstant.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true) //EnableGlobalMethodSecurity
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;


    private final String ADMIN = "ADMIN";
    private final String USER = "USER";


    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Pwd");
        return new BCryptPasswordEncoder(7);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin(corsAllowedOrigin); // @Value: http://localhost:8080
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowCredentials(true);


//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.addAllowedHeader(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        System.out.println("filter chain");
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.headers().httpStrictTransportSecurity().disable();
        // set route sẽ ăn từ trên xuống (ưu tiên cái đầu tiên)

        http
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .securityMatcher("/**")
                .authorizeHttpRequests(
                        register -> register

                                // ALL
                                .requestMatchers(HttpMethod.GET, GET_AUTH_WHITELIST).permitAll()
                                .requestMatchers(HttpMethod.POST, POST_AUTH_WHITELIST).permitAll()

                                // Only ADMIN
                                .requestMatchers(HttpMethod.GET, GET_ADMIN_PATH).permitAll() // hasRole(ADMIN)
                                .requestMatchers(HttpMethod.DELETE, DELETE_ADMIN_PATH).permitAll() // hasRole(ADMIN)
                                .requestMatchers(HttpMethod.POST, POST_ADMIN_PATH).permitAll()//.hasRole(ADMIN)
                                .requestMatchers(HttpMethod.PUT, PUT_ADMIN_PATH).permitAll()//.hasRole(ADMIN)
                                .requestMatchers(HttpMethod.PATCH, PATCH_ADMIN_PATH).permitAll()//.hasRole(ADMIN)

                                // Only USER
                                .requestMatchers(HttpMethod.GET, GET_USER_PATH).permitAll() // hasRole(USER)
                                .requestMatchers(HttpMethod.POST, POST_USER_PATH).permitAll() // hasRole(USER)
                                .requestMatchers(HttpMethod.PUT, PUT_USER_PATH).permitAll() // hasRole(USER)
                                .requestMatchers(HttpMethod.DELETE, DELETE_USER_PATH).permitAll() // hasRole(USER)

                                .requestMatchers("/**").permitAll()


                                .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
