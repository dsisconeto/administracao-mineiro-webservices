package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.DomainException;

public interface CategoryService {

    Category getByIdOrFail(long categoryId) throws CategoryNotFoundException;

    void existsByIdOrFail(long categoryId) throws DomainException;
}
