package com.example.backend.post;

/**
 * Comment
 *
 * @author JuliusR
 */
public record Comment(Long postId, Long id, String name, String email,
        String body) {
}
