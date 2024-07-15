package br.com.ForumHub.Domain.Topic.Validations;

import br.com.ForumHub.Domain.Topic.DataUpdateInfos;
import br.com.ForumHub.Exceptions.CustomException;
import br.com.ForumHub.Repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepeatedUpdateTopic implements UpdateTopicValidator{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void updateValidator(DataUpdateInfos data) throws CustomException {
        boolean exists = topicRepository.existsByTitleAndMessage(data.title(), data.message());
        if (exists) {
            throw new CustomException("A topic with that name or message already exists.");
        }
    }
}
