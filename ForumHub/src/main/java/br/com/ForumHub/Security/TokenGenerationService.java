package br.com.ForumHub.Security;

import br.com.ForumHub.Domain.User.User;
import br.com.ForumHub.Exceptions.CustomException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenGenerationService {
    public String GenerationToken(User user) throws CustomException {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            String token = JWT.create()
                    .withIssuer("ForúmHub development team.")
                    .withSubject(user.getEmail())
                    .withExpiresAt(TokenExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new CustomException("Error ao gerar o Token JWT.", exception);
        }
    }

    private Instant TokenExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
