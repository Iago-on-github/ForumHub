package br.com.ForumHub.Domain.Topic.Validations;

import br.com.ForumHub.Domain.Topic.DataRegistrationTopic;
import br.com.ForumHub.Domain.Topic.DataUpdateInfos;
import br.com.ForumHub.Exceptions.CustomException;
import br.com.ForumHub.Repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepeatedTopicValidator implements TopicValidator{
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validator(DataRegistrationTopic data) throws CustomException {
        boolean existis = topicRepository.existsByTitleAndMessage(data.title(), data.message());
        if (existis) {
            throw new CustomException("A topic with that name or message already exists.");
        }
    }
}
