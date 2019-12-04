package com.mercado.mineiro.administration.bill.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryRepository _categoryRepository;

    CategoryService(ICategoryRepository categoryRepository) {
        _categoryRepository = categoryRepository;
    }

    @Override
    public Category getByIdOrFail(long categoryId) throws CategoryNotFoundException {
        return _categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
}
