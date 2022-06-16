package com.example.backend.config.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * SecurityConfig
 *
 * @author JuliusR
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    private SecurityProperties properties;

    public SecurityConfig(SecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .csrf(conf -> conf.disable())
                .authorizeExchange(requests -> requests.anyExchange().authenticated())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails builtInUser = User.builder()
                .username(properties.getLogin())
                .password(properties.getPassword())
                .passwordEncoder(value -> passwordEncoder().encode(value))
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(builtInUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
