package br.com.ForumHub.Domain.Topic.Validations;

import br.com.ForumHub.Domain.Topic.DataUpdateInfos;
import br.com.ForumHub.Exceptions.CustomException;

public interface UpdateTopicValidator {
    void updateValidator(DataUpdateInfos data) throws CustomException;
}
