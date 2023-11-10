package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.repositoryyourplus.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String PRODUCTO = "/yourplus/v1/productos";
    private static final String ADMIN = "administrador";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(/*HttpMethod.POST,*/"/yourplus/v1/**")
                .permitAll()
//                .antMatchers("/yourplus/v1/laboratorios/**")
//                .hasAuthority(ADMIN)
//                .antMatchers(HttpMethod.POST, PRODUCTO)
//                .hasAuthority(ADMIN)
//                .antMatchers(HttpMethod.PUT, PRODUCTO)
//                .hasAuthority(ADMIN)
//                .antMatchers(HttpMethod.DELETE, PRODUCTO)
//                .hasAuthority(ADMIN)
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
