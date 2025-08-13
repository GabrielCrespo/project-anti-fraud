package br.com.gcs.ms_order_service.exception;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorMessage> jwtException(HttpServletRequest http, JwtException e) {
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value(), http.getRequestURI(),
                Instant.now(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(HttpServletRequest http, ResourceNotFoundException e) {
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), http.getRequestURI(),
                Instant.now(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(HttpServletRequest http, MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), http.getRequestURI(),
                Instant.now(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorMessage);
    }

}
