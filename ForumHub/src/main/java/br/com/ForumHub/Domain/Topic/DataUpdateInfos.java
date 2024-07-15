package br.com.ForumHub.Domain.Topic;

import jakarta.validation.constraints.NotNull;

public record DataUpdateInfos(
                              String title,
                              String message) {
    public DataUpdateInfos(Topic topic) {
        this(topic.getTitle(), topic.getMessage());
    }
}
