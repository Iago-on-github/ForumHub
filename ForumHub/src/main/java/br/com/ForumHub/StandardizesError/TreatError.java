package br.com.ForumHub.StandardizesError;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TreatError {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400(MethodArgumentNotValidException argumentNotValidException) {
        var error = argumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DataValidationError::new).toList());
    }
}
