package com.mercado.mineiro.administration;

import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.common.exception.EntityNotFoundException;
import com.mercado.mineiro.administration.common.web.Responses;
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

    @ExceptionHandler(value = DomainException.class)
    protected ResponseEntity<Object> handleDomainException(DomainException e, WebRequest request) {
        return Responses.unprocessableEntity(e);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        return Responses.notFound();
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


        return Responses.unprocessableEntity("Dados inválidos", errors);
    }
}
