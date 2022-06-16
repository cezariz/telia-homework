package com.example.backend.post;

/**
 * PostInput
 *
 * @author JuliusR
 */
public record PostInput(Long userId, String title, String body) {
}
