package br.com.challenger.backendchallenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
