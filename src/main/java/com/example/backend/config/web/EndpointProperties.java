package com.example.backend.config.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * EndpointProperties
 *
 * @author JuliusR
 */
@Validated
@ConfigurationProperties("web.service")
public class EndpointProperties {

    @NotEmpty
    private String endpointUrl;

    @Min(0)
    private int connectTimeout = 3000;

    @Min(0)
    private int readTimeout = 3000;

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(final String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(final int readTimeout) {
        this.readTimeout = readTimeout;
    }

}
