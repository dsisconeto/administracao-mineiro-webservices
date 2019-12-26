package com.mercado.mineiro.administration.bill.category;

import com.mercado.mineiro.administration.common.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class CategoryControllerTest extends ControllerTestBase {

    @MockBean
    private CategoryService categoryService;

    @Test
    void should_return_created_status() throws Exception {

        var form = new CategoryFormRequest();
        form.setName(faker.name().title());

        Mockito.when(categoryService.create(form))
                .thenReturn(new Category(1L, form.getName()));


        var requestBuilder = post(CategoryController.PATH + "/", form);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void should_return_no_content_status_when_put() throws Exception {
        var form = new CategoryFormRequest();
        form.setName(faker.name().title());
        var category = new Category(1L, form.getName());

        Mockito.when(categoryService.update(category.getId(), form))
                .thenReturn(category);

        var requestBuilder = put(CategoryController.PATH + "/" + category.getId(), form);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void should_return_not_content_when_destroy() throws Exception {
        var category = new Category(1L, faker.name().title());

        var requestBuilder = MockMvcRequestBuilders.delete(
                CategoryController.PATH + "/" + category.getId()
        );

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(categoryService).delete(category.getId());
    }


}
