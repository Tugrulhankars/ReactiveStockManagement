package org.example.reactivestockmanagement.dto.request;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
