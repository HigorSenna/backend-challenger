package br.com.challenger.backendchallenger.exception;

import br.com.challenger.backendchallenger.dto.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GeneralErrorException.class, NotFoundException.class, BusinessException.class})
    protected ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest request) {
        ResponseDTO response = new ResponseDTO();

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        response.setCode(responseStatus.code().value());

        if(StringUtils.isNotBlank(ex.getMessage())) {
            response.setMessage(ex.getMessage());
        }
        else {
            response.setMessage(responseStatus.reason());
        }

        return super.handleExceptionInternal(ex,response, this.getHeaderJson(), responseStatus.code(), request);
    }

    private HttpHeaders getHeaderJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
