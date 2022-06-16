package com.example.backend.user;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * UserController
 *
 * @author JuliusR
 */
@Controller
public class UserGraphqlController {

    private final UserService userService;

    public UserGraphqlController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    Flux<User> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    Mono<User> userById(@Argument Long id) {
        return userService.getUserById(id);
    }

}
