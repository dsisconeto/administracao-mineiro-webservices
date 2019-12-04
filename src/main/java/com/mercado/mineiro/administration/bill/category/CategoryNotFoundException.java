package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException() {
        super("Categoria não encontrada");

    }

}
