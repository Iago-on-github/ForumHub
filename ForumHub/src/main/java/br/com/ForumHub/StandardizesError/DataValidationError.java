package br.com.ForumHub.StandardizesError;

import org.springframework.validation.FieldError;

public record DataValidationError(String title, String message) {
    public DataValidationError(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
