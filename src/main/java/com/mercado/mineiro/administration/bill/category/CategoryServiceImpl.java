package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.DomainException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getByIdOrFail(long categoryId) throws CategoryNotFoundException {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void existsByIdOrFail(long categoryId) throws DomainException {
        if (categoryRepository.existsById(categoryId)) return;

        throw new DomainException("Categoria inválida");
    }
}
