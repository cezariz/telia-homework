package com.example.backend.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service layer for User
 *
 * @author JuliusR
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private static final String ID = "id";
    private static final String USERS_URI = "/users";

    private final WebClient jsonplaceholderApiClient;

    /**
     * Class contructor
     */
    public UserService(final WebClient jsonplaceholderApiClient) {
        super();
        this.jsonplaceholderApiClient = jsonplaceholderApiClient;
    }

    /**
     * Returns all users
     *
     * @return Flux {@link Flux} of type {@link User}
     */
    public Flux<User> getAllUsers() {
        LOG.debug("Fetching all users.");

        return jsonplaceholderApiClient
                .get()
                .uri(USERS_URI)
                .retrieve()
                .bodyToFlux(User.class);
    }

    /**
     * Returns User by id
     *
     * @return Mono of type {@link User}
     */
    public Mono<User> getUserById(final Long id) {
        LOG.debug("Fetching user with id {}.", id);

        String uri = UriComponentsBuilder
                .fromUriString(USERS_URI + "/{id}")
                .build()
                .toUriString();

        Map<String, Object> uriVariables = Map.of(ID, id);

        return jsonplaceholderApiClient
                .get()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToMono(User.class);
    }

}
