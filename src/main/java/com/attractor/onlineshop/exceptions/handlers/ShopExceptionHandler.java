package com.attractor.onlineshop.exceptions.handlers;

import com.attractor.onlineshop.exceptions.QuantityErrorException;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.ReviewNotAllowedException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ShopExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<?> handleAllException(
            ResourceNotFoundException ex, WebRequest request) {
        var body = new ExceptionResponse(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        var body = new ExceptionResponse(LocalDateTime.now(),
                "Validation failed", ex.getBindingResult().getAllErrors().toString());
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ReviewNotAllowedException.class)
    public ResponseEntity<?> reviewException(ReviewNotAllowedException ex,
                                             WebRequest request){
        var body=new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.badRequest().body(body);

    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> reviewException(UserNotFoundException ex,
                                             WebRequest request){
        var body=new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);

    }

    @ExceptionHandler(QuantityErrorException.class)
    public ResponseEntity<?> reviewException(QuantityErrorException ex,
                                             WebRequest request){
        var body=new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.badRequest().body(body);

    }
}
