package br.com.ForumHub.Security;

import jakarta.validation.constraints.NotBlank;

public record DataAuthentication(@NotBlank String login,
                                 String email,
                                 @NotBlank String password) {
}
