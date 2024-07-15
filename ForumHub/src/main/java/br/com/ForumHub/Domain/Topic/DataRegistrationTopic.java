package br.com.ForumHub.Domain.Topic;

import br.com.ForumHub.Domain.User.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DataRegistrationTopic(
                                    Long id,
                                    @NotNull(message = "O campo 'title' é obrigatório.")
                                    String title,
                                    @NotBlank(message = "O campo 'message' é obrigatório.")
                                    String message,
                                    @NotNull
                                    topicStatus topicStatus,
                                    @NotNull
                                    User author,
                                    @NotNull(message = "O campo 'course' é obrigatório.")
                                    String course)
{
    public DataRegistrationTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getTopicStatus(), topic.getAuthor(), topic.getCourse());
    }
}
