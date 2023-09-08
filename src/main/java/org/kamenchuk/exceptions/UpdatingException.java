package org.kamenchuk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UpdatingException extends RuntimeException{

    public UpdatingException(String message){
        super(message);
        System.out.println(message);
    }
}
