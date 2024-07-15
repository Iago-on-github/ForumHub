package br.com.ForumHub.Domain.Topic;

import br.com.ForumHub.Domain.User.User;

public record DataTopicList(
        Long id,
        String title,
        String message,
        topicStatus topicStatus,
        User author,
        String course
) {
    public DataTopicList(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getTopicStatus(), topic.getAuthor(), topic.getCourse());
    }
}
