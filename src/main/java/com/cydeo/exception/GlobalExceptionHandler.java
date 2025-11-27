package com.cydeo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> userNotFoundException(UserNotFoundException exception){

        exception.printStackTrace();

        String message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionWrapper(message, HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionWrapper> userAlreadyExistsException(UserAlreadyExistsException exception){

        exception.printStackTrace();

        String message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionWrapper(message, HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> genericException(Throwable exception){

        exception.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionWrapper("An error occurred. Try again later", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleValidationError(MethodArgumentNotValidException exception, HttpServletRequest request){

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid Input(s)", HttpStatus.BAD_REQUEST.value());

        exceptionWrapper.setPath(request.getRequestURI());

        List<ValidationException> validationExceptions = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {

            String errorField = ((FieldError) error).getField();
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            String reason = error.getDefaultMessage();

            ValidationException validationException = new ValidationException(errorField, rejectedValue, reason);

            validationExceptions.add(validationException);
        }

        exceptionWrapper.setValidationExceptionList(validationExceptions);
        exceptionWrapper.setErrorCount(validationExceptions.size());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);
    }
}
