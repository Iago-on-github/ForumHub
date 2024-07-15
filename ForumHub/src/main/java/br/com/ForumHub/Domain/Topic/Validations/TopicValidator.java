package br.com.ForumHub.Domain.Topic.Validations;

import br.com.ForumHub.Domain.Topic.DataRegistrationTopic;
import br.com.ForumHub.Domain.Topic.DataUpdateInfos;
import br.com.ForumHub.Exceptions.CustomException;

public interface TopicValidator {
    void validator(DataRegistrationTopic data) throws CustomException;
}
