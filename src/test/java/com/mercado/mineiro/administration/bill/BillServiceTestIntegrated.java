package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.Bill;
import com.mercado.mineiro.administration.bill.BillRepository;
import com.mercado.mineiro.administration.bill.BillService;
import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.category.CategoryRepository;
import com.mercado.mineiro.administration.common.ServiceIntegratedTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest

public class BillServiceTestIntegrated extends ServiceIntegratedTestBase {

    @Autowired
    private BillService billService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @MockBean
    private ChangeStatusEventHandler changeStatusEventHandler;

    @Test
    void paid() {
        var category = new Category();
        category.setName(faker.name().title());


        categoryRepository.save(category);
        var request = new BillCreateRequestDTO();

        request.setCategoryId(category.getId());
        request.setDescription(faker.name().title());
        request.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 1000)));
        request.setPayIn(LocalDate.now());

        billService.create(request);

        Mockito.verify(
                changeStatusEventHandler,
                Mockito.times(1)
        ).handler(ArgumentMatchers.any());
    }


}
