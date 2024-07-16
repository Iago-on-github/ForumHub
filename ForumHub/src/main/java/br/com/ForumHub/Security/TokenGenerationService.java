package br.com.ForumHub.Security;

import br.com.ForumHub.Domain.User.User;
import br.com.ForumHub.Exceptions.CustomException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenGenerationService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String GenerationToken(User user) throws CustomException {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ForúmHub development team.")
                    .withSubject(user.getLogin())
                    .withExpiresAt(TokenExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new CustomException("Error ao gerar o Token JWT.", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ForúmHub development team.")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("TokenJWT invalid or expired.");
        }
    }

    private Instant TokenExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
