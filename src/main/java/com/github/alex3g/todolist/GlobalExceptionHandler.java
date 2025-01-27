package com.github.alex3g.todolist;

import com.github.alex3g.todolist.user.InvalidUserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<StandardError> illegalArgument(InvalidUserException exception, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
}
