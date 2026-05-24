package com.example.prova.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleBusinessErrors(IllegalArgumentException ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleBusinessErrors(DataIntegrityViolationException ex, WebRequest request) {
        // Restituisce un 422 con il messaggio di errore personalizzato
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBusinessErrors(ResourceNotFoundException ex, WebRequest request) {
        // Restituisce un 404 con il messaggio di errore personalizzato
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        ));
    }

}
