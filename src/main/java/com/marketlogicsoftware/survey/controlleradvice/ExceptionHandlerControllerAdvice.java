package com.marketlogicsoftware.survey.controlleradvice;

import com.marketlogicsoftware.survey.exception.UnfoundEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnfoundEntity.class)
    public final ResponseEntity<String> handleEntityNotFoundException(UnfoundEntity e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
