package com.example.backend.post;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PostGraphqlController
 *
 * @author JuliusR
 */
@Controller
public class PostGraphqlController {
    private final PostService postService;

    public PostGraphqlController(final PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    Flux<Post> posts() {
        return postService.getAllPosts();
    }

    @QueryMapping
    Flux<Post> getPostsByUserId(@Argument final Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @QueryMapping
    Mono<Post> getPostById(@Argument final Long postId) {
        return postService.getPostById(postId);
    }

    @MutationMapping
    Mono<Post> createPost(@Argument PostInput post) {
        return postService.createPost(post);
    }

    @QueryMapping
    Flux<Comment> getCommentsByPostId(@Argument final Long postId) {
        return postService.getCommentsByPostId(postId);
    }

}
