package com.example.backend.user;

/**
 * Address
 *
 * @author JuliusR
 */
public record Address(String street, String suite, String city, String zipcode,
        Geo geo) {
}
