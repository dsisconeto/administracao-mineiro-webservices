package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.exception.DomainException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category create(CategoryFormRequest request) {

        var category = modelMapper.map(request, Category.class);

        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(Long id, CategoryFormRequest request) {

        var category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        modelMapper.map(request, category);

        categoryRepository.save(category);

        return category;
    }

    @Override
    public void delete(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        category.markAsDeleted();

        categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAllPaginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getByIdOrFail(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DomainException("Categoria inválida"));
    }

    @Override
    public void existsByIdOrFail(Long categoryId) throws DomainException {
        if (categoryRepository.existsById(categoryId)) return;

        throw new DomainException("Categoria inválida");
    }
}
