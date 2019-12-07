package com.mercado.mineiro.administration;

import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.common.EntityNotFoundException;
import com.mercado.mineiro.administration.common.web.ResponseFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    private ResponseFactory responseFactory;

    public HandlerException(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = DomainException.class)
    protected ResponseEntity<Object> handleDomainException(DomainException e, WebRequest request) {
        return responseFactory.unprocessableEntity(e);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        return responseFactory.notFound();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        var errors = new LinkedHashMap<String, String>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });


        return responseFactory.unprocessableEntity("Dados inválidos", errors);
    }
}
