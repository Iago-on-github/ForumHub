package br.com.ForumHub.Security;

import jakarta.validation.constraints.NotBlank;

public record DataAuthentication(@NotBlank String email, @NotBlank String password) {
}
