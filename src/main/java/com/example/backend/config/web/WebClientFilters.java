package com.example.backend.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

/**
 * WebClientFilters
 *
 * @author JuliusR
 */
public class WebClientFilters {

   private static final Logger LOG = LoggerFactory.getLogger(WebClientFilters.class);

   public static ExchangeFilterFunction loggingFilter() {
        ExchangeFilterFunction loggingFilter = (clientRequest, nextFilter) -> {
            LOG.debug("Sending request {} {}", clientRequest.method(), clientRequest.url());

            return nextFilter.exchange(clientRequest);
        };
        return loggingFilter;
    }

}
