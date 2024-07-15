package br.com.ForumHub.Domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegistrationUser(Long id,
                                   @NotNull
                                   String name,
                                   @NotBlank
                                   String email,
                                   @NotNull
                                   String password) {
    public DataRegistrationUser(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
