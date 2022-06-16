package com.example.backend.post;

/**
 * Post
 *
 * @author JuliusR
 */
public record Post(Long userId, Long id, String title, String body) {
}
