package org.kamenchuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.springdoc.api.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAdvicer {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> foundException(Exception exception) {
        ResponseEntity<ErrorMessage> response = null;
        ErrorMessage message = null;
        log.error(exception.getClass().getName(), exception.getMessage());
        if (ResourceNotFoundException.class.equals(exception.getClass())) {
            message = new ErrorMessage(exception.getMessage());
            response = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (CreationException.class.equals(exception.getClass())) {
            message = new ErrorMessage(exception.getMessage());
            response = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (UpdatingException.class.equals(exception.getClass())) {
            message = new ErrorMessage(exception.getMessage());
            response = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(HttpMessageNotReadableException.class.equals(exception.getClass())
                || DataIntegrityViolationException.class.equals(exception.getClass())){
            message = new ErrorMessage("Invalid format of request");
            response = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        else {
            message = new ErrorMessage("AAAAAAAAAAAAAAAA");
            response = new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
