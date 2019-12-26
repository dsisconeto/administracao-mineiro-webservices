package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.ServiceTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

class CategoryServiceTest extends ServiceTestBase {


    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;


    @Test
    void given_valid_name_when_create_then_create_category() {
        var form = new CategoryFormRequest();
        form.setName(faker.name().title());

        var category = categoryService.create(form);

        Mockito.verify(categoryRepository).save(category);
        Assertions.assertEquals(category.getName(), form.getName());
    }

    @Test
    void given_valid_name_when_update_then_update_category() {
        var form = new CategoryFormRequest();
        var categoryToUpdate = new Category(1L, faker.name().title());
        form.setName(faker.name().title());
        Mockito.when(categoryRepository.findById(categoryToUpdate.getId()))
                .thenReturn(Optional.of(categoryToUpdate));

        var categoryUpdated = categoryService.update(categoryToUpdate.getId(), form);

        Assertions.assertEquals(form.getName(), categoryUpdated.getName());
        Assertions.assertEquals(categoryToUpdate.getId(), categoryUpdated.getId());
    }

    @Test
    void give_invalid_category_id_when_update_then_throw_category_not_found_exception() {
        var form = new CategoryFormRequest();
        var categoryToUpdate = new Category(1L, faker.name().title());
        form.setName(faker.name().title());
        Mockito.when(categoryRepository.findById(categoryToUpdate.getId()))
                .thenReturn(Optional.empty());


        Assertions.assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.update(categoryToUpdate.getId(), form)
        );
    }


    @Test
    void give_valid_category_id_when_delete_then_safe_delete_category() {
        var categoryToDelete = new Category(1L, faker.name().title());
        Mockito.when(categoryRepository.findById(categoryToDelete.getId()))
                .thenReturn(Optional.of(categoryToDelete));

        categoryService.delete(categoryToDelete.getId());

        Assertions.assertNotNull(categoryToDelete.getDeletedAt());

    }

    @Test
    void give_invalid_category_id_when_delete_then_throw_category_not_found_exception() {

        var categoryToDelete = new Category(1L, faker.name().title());
        Mockito.when(categoryRepository.findById(categoryToDelete.getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.delete(categoryToDelete.getId())
        );

    }
}
