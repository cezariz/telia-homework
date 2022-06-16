package com.example.backend.config.web;

import java.time.Duration;

import com.example.backend.interceptors.RestTemplateHeaderModifierInterceptor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestClientConfig
 *
 * @author JuliusR
 */
@Configuration
@EnableConfigurationProperties(EndpointProperties.class)
public class RestClientConfig {

    private final EndpointProperties properties;

    public RestClientConfig(final EndpointProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(properties.getEndpointUrl())
                .interceptors(new RestTemplateHeaderModifierInterceptor())
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .build();
    }

}
