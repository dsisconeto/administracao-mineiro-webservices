package com.mercado.mineiro.administration.supplier;

import com.mercado.mineiro.administration.common.exception.EntityNotFoundException;

public class SupplierNotFoundException extends EntityNotFoundException {

    public SupplierNotFoundException() {
        super("Fornecedor não encontrado");
    }
}
