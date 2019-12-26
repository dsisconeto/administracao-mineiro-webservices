package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.exception.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category create(CategoryFormRequest request);

    Category update(Long id, CategoryFormRequest request);

    void delete(Long id);

    Page<Category> findAllPaginate(Pageable pageable);

    Category getByIdOrFail(Long categoryId) throws CategoryNotFoundException;

    void existsByIdOrFail(Long categoryId) throws DomainException;
}
