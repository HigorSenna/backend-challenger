package br.com.challenger.backendchallenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro geral, tente novamente mais tarde.")
public class GeneralErrorException extends RuntimeException {

    public GeneralErrorException(String message) {
        super(message);
    }

    public GeneralErrorException(){}
}
