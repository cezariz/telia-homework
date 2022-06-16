package com.example.backend.config.web;

import java.time.Duration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

/**
 * WebClientConfig
 *
 * @author JuliusR
 */
@Configuration
@EnableConfigurationProperties(EndpointProperties.class)
public class WebClientConfig {

    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";

    private final EndpointProperties properties;

    public WebClientConfig(final EndpointProperties properties) {
        this.properties = properties;
    }

    @Bean
    WebClient jsonplaceholderApiClient() {
        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .defaultHeaders(headersConsumer -> {
                    headersConsumer.addIfAbsent(
                            HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
                })
                .filter(WebClientFilters.loggingFilter())
                .baseUrl(properties.getEndpointUrl())
                .build();
    }

    private HttpClient httpClient() {
        return HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                .responseTimeout(Duration.ofMillis(properties.getReadTimeout()));
    }

}
