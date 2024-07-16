package br.com.ForumHub.Controllers;

import br.com.ForumHub.Domain.Topic.*;
import br.com.ForumHub.Domain.Topic.Validations.TopicValidator;
import br.com.ForumHub.Domain.Topic.Validations.UpdateTopicValidator;
import br.com.ForumHub.Exceptions.CustomException;
import br.com.ForumHub.Repositories.TopicRepository;
import br.com.ForumHub.Repositories.UserRepository;
import br.com.ForumHub.Domain.User.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicValidator topicValidator;

    @Autowired
    private UpdateTopicValidator updateTopicValidator;

    // create topic
    @PostMapping
    @Transactional
    public ResponseEntity registerTopic(@RequestBody @Valid DataRegistrationTopic data, UriComponentsBuilder uriBuilder) throws CustomException {
        topicValidator.validator(data);
        User topicAuthor = userRepository.getReferenceById(data.author().getId());
        var topic = new Topic(data);
        topicRepository.save(topic);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataRegistrationTopic(topic));
    }

    // topic list
    @GetMapping
    @Transactional
    public ResponseEntity<Page<DataTopicList>> activeTopics(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        var pageListActive = topicRepository.findAllByTopicStatus(topicStatus.ATIVO, pageable)
                .map(DataTopicList::new);
        return ResponseEntity.ok(pageListActive);
    }

    // topic breakdown
    @GetMapping("/{id}")
    public ResponseEntity topicBreakdown (@PathVariable Long id) {
        var topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicBreakdownData(topic));
    }

    // update topic

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody @Valid DataUpdateInfos data) throws CustomException {
        var topic = topicRepository.getReferenceById(id);
        updateTopicValidator.updateValidator(data);
        topic.updateInfos(data);
        return ResponseEntity.ok(new DataUpdateInfos(topic));
    }

    // detele topic
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        var topic = topicRepository.getReferenceById(id);
        topic.setTopicStatus(topicStatus.INATIVO);
        topicRepository.save(topic);
        return ResponseEntity.noContent().build();
    }
}
