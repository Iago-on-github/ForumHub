package br.com.ForumHub.Domain.Topic;

import br.com.ForumHub.Domain.User.User;

public record TopicBreakdownData(String title, String message, topicStatus topicStatus, User author, String course) {
    public TopicBreakdownData(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getTopicStatus(), topic.getAuthor(), topic.getCourse());
    }
}
