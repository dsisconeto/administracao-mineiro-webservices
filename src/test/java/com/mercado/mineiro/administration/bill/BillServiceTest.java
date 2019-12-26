package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.category.CategoryService;

import com.mercado.mineiro.administration.bill.document.type.DocumentTypeService;
import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.supplier.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillServiceTest {

    private CategoryService categoryService;
    private BillRepository billRepository;
    private BillServiceImp billService;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        billRepository = Mockito.mock(BillRepository.class);
        DocumentTypeService documentTypeService = Mockito.mock(DocumentTypeService.class);
        SupplierService supplierService = Mockito.mock(SupplierService.class);
        billService = new BillServiceImp(
                billRepository,
                categoryService,
                supplierService,
                documentTypeService,
                modelMapper
        );
    }

    @Test
    void should_create_one_bill() throws CategoryNotFoundException {

        var request = new BillCreateRequestDTO();
        request.setAmount(BigDecimal.valueOf(10));
        request.setDescription("Conta de água");
        request.setPayIn(LocalDate.now());
        request.setCategoryId(1L);
        request.setSupplierId(1L);
        request.setDocumentCode("123456");
        request.setDocumentTypeId(1L);


        Mockito.when(categoryService.getByIdOrFail(request.getCategoryId()))
                .thenReturn(new Category(request.getCategoryId(), "Categoria"));


        var bill = billService.create(request);
        Mockito.verify(billRepository).save(bill);

        assertEquals(request.getDescription(), bill.getDescription());
        assertEquals(request.getAmount(), bill.getAmount());
        assertEquals(request.getPayIn(), bill.getPayIn());
        assertEquals(request.getCategoryId(), bill.getCategory().getId());
        assertEquals(request.getCategoryId(), bill.getCategory().getId());
        assertEquals(request.getSupplierId(), bill.getSupplier().getId());
        assertEquals(request.getSupplierId(), bill.getSupplier().getId());
        assertEquals(request.getDocumentCode(), bill.getDocument().getCode());
        assertEquals(request.getDocumentTypeId(), bill.getDocument().getType().getId());
    }


    @Test
    void should_update_bill() throws DomainException {

        var request = new BillUpdateRequestDTO();


        request.setBillId(1L);
        request.setAmount(BigDecimal.valueOf(10));
        request.setDescription("Conta de água");
        request.setPayIn(LocalDate.now());
        request.setCategoryId(1L);
        request.setSupplierId(1L);
        request.setDocumentCode("123456");
        request.setDocumentTypeId(1L);


        Mockito.when(categoryService.getByIdOrFail(request.getCategoryId()))
                .thenReturn(new Category(request.getCategoryId(), "Categoria"));


        Mockito.when(billRepository.findById(request.getBillId())).thenReturn(Optional.of(new Bill()));

        var bill = billService.update(request);
        Mockito.verify(billRepository).save(bill);

        assertEquals(request.getDescription(), bill.getDescription());
        assertEquals(request.getAmount(), bill.getAmount());
        assertEquals(request.getPayIn(), bill.getPayIn());
        assertEquals(request.getCategoryId(), bill.getCategory().getId());
        assertEquals(request.getCategoryId(), bill.getCategory().getId());
        assertEquals(request.getSupplierId(), bill.getSupplier().getId());
        assertEquals(request.getSupplierId(), bill.getSupplier().getId());
        assertEquals(request.getDocumentCode(), bill.getDocument().getCode());
        assertEquals(request.getDocumentTypeId(), bill.getDocument().getType().getId());
    }



}
