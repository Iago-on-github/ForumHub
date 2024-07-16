package br.com.ForumHub.Controllers;

import br.com.ForumHub.Domain.User.DataRegistrationUserResponse;
import br.com.ForumHub.Repositories.UserRepository;
import br.com.ForumHub.Domain.User.DataRegistrationUser;
import br.com.ForumHub.Domain.User.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //create new user
    @PostMapping
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid DataRegistrationUser data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
        userRepository.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataRegistrationUserResponse(user));
    }
}
