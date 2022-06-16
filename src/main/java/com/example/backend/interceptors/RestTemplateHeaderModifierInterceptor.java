package com.example.backend.interceptors;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * RestTemplateHeaderModifierInterceptor
 *
 * @author JuliusR
 */
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(RestTemplateHeaderModifierInterceptor.class);

    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        LOG.debug("adding CONTENT_TYPE header to URI: {}", request.getURI());

        request
                .getHeaders()
                .addIfAbsent(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }

}
