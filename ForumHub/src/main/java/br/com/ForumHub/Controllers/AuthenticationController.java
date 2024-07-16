package br.com.ForumHub.Controllers;

import br.com.ForumHub.Domain.User.DataTokenJWT;
import br.com.ForumHub.Domain.User.User;
import br.com.ForumHub.Exceptions.CustomException;
import br.com.ForumHub.Security.DataAuthentication;
import br.com.ForumHub.Security.TokenGenerationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenGenerationService tokenGenerationService;

    @PostMapping
    @Transactional
    public ResponseEntity authenticationUser(@RequestBody @Valid DataAuthentication data) throws CustomException {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);
        var tokenGeneration = tokenGenerationService.GenerationToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenGeneration));
    }
}
