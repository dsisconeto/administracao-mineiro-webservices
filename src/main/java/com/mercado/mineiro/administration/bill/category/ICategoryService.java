package com.mercado.mineiro.administration.bill.category;

public interface ICategoryService {

    Category getByIdOrFail(long categoryId) throws CategoryNotFoundException;

}
