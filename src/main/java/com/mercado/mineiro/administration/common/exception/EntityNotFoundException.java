package com.mercado.mineiro.administration.common.exception;

import com.mercado.mineiro.administration.common.exception.DomainException;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
