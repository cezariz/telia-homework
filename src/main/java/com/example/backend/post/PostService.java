package com.example.backend.post;

import java.time.Duration;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service layer for {@link Post}
 *
 * @author JuliusR
 */
@Service
public class PostService {

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private static final String COMMENTS_URI = "/comments";
    private static final String ID = "id";
    private static final String POSTS_URI = "/posts";
    private static final String POST_ID = "postId";
    private static final String USERS_URI = "/users";

    private final WebClient jsonplaceholderApiClient;

    private Flux<Post> cachedPosts;

    /**
     * Class contructor
     */
    public PostService(final WebClient jsonplaceholderApiClient) {
        super();
        this.jsonplaceholderApiClient = jsonplaceholderApiClient;
    }

    @PostConstruct
    public void init() {
        cachedPosts = getAllPosts().cache(100, Duration.ofSeconds(3));
    }

    /**
     * Returns all cached posts fetched from jsonplaceholderApi
     *
     * @return Flux of type {@link Post}
     */
    public Flux<Post> getCachedPosts() {
        return cachedPosts;
    }

    /**
     * Fetches all posts
     *
     * @return Flux of type {@link Post}
     */
    private Flux<Post> getAllPosts() {
        LOG.debug("Fetching all Posts.");

        return jsonplaceholderApiClient
                .get()
                .uri(POSTS_URI)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    /**
     * Fetches all posts of a user by user id
     *
     * @return Flux of type {@link Post}
     */
    public Flux<Post> getPostsByUserId(final Long userId) {
        LOG.debug("Fetching posts of a user with id {}.", userId);

        String uri = USERS_URI + "/{userId}" + POSTS_URI;

        Map<String, Object> uriVariables = Map.of("userId", userId);

        return jsonplaceholderApiClient
                .get()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    /**
     * Fetches Post by id
     *
     * @return Mono of type {@link Post}
     */
    public Mono<Post> getPostById(final Long id) {
        LOG.debug("Fetching a post with id {}.", id);

        String uri = UriComponentsBuilder
                .fromUriString(POSTS_URI + "/{id}")
                .build()
                .toUriString();

        Map<String, Object> uriVariables = Map.of(ID, id);

        return jsonplaceholderApiClient
                .get()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToMono(Post.class);
    }

    /**
     * Fetches comments of a post by id
     *
     * @return Mono of saved {@link Post}
     */
    public Flux<Comment> getCommentsByPostId(final Long postId) {
        LOG.debug("Fetching comments of a post with id {}.", postId);

        String uri = UriComponentsBuilder
                .fromUriString(COMMENTS_URI)
                .queryParam(POST_ID, postId)
                .build()
                .toUriString();

        return jsonplaceholderApiClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Comment.class);
    }

    /**
     * Creates new post
     *
     * @return Mono of saved {@link Post}
     */
    public Mono<Post> createPost(final PostInput post) {
        LOG.debug("Creating new post {}.", post.toString());

        return jsonplaceholderApiClient
                .post()
                .uri(POSTS_URI)
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }

}
