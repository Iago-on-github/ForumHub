package br.com.ForumHub.Domain.User;

public record DataRegistrationUserResponse(Long id,
                                           String login,
                                           String email) {
    public DataRegistrationUserResponse(User user) {
        this(user.getId(), user.getLogin(), user.getEmail());
    }
}
