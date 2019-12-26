package com.mercado.mineiro.administration.bill.document.type;

import com.mercado.mineiro.administration.common.exception.DomainException;

public class DocumentTypeNotFoundException extends DomainException {

    public DocumentTypeNotFoundException() {
        super("Tipo do documento não encontrado");
    }
}
