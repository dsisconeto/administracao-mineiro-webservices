package com.mercado.mineiro.administration.supplier;

import com.mercado.mineiro.administration.common.EntityNotFoundException;

public class SupplierNotFoundException extends EntityNotFoundException {

    public SupplierNotFoundException() {
        super("Fornecedor não encontrado");
    }
}
