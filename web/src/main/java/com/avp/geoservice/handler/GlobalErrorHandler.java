package com.avp.geoservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception error) {
        if (error instanceof ConstraintViolationException ||
                error instanceof MethodArgumentTypeMismatchException ||
                error instanceof MissingServletRequestParameterException ||
                error instanceof MethodArgumentNotValidException ||
                error instanceof BindException
        ) {
            return new ResponseEntity(error.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(error.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
