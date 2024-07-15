package br.com.ForumHub.Repositories;

import br.com.ForumHub.Domain.Topic.Topic;
import br.com.ForumHub.Domain.Topic.topicStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String title, String message);
    Page<Topic> findAllByTopicStatus(topicStatus topicStatus, Pageable pageable);
}
