package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.category.ICategoryService;
import com.mercado.mineiro.administration.bill.document.type.DocumentType;
import com.mercado.mineiro.administration.bill.document.type.IDocumentTypeRepository;

import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.supplier.ISupplierRepository;
import com.mercado.mineiro.administration.supplier.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class BillServiceTest {

    private ICategoryService categoryService;
    private IBillRepository billRepository;
    private IDocumentTypeRepository documentTypeRepository;
    private ISupplierRepository supplierRepository;
    private BillService billService;

    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(ICategoryService.class);
        billRepository = Mockito.mock(IBillRepository.class);
        documentTypeRepository = Mockito.mock(IDocumentTypeRepository.class);
        supplierRepository = Mockito.mock(ISupplierRepository.class);
        billService = new BillService(billRepository, categoryService, documentTypeRepository, supplierRepository);
    }

    @Test
    void should_store_bill() throws CategoryNotFoundException {

        var request = new BillRequestStoreForTest();


        request.setAmount(BigDecimal.valueOf(10));
        request.setDescription("Conta de água");
        request.setPayIn(LocalDate.now());
        request.setCategoryId(1L);
        request.setSupplierId(1L);
        request.setDocumentCode("123456");
        request.setDocumentTypeId(1L);


        Mockito.when(categoryService.getByIdOrFail(request.getCategoryId()))
                .thenReturn(new Category(request.getCategoryId(), "Categoria"));

        Mockito.when(supplierRepository.findById(request.getCategoryId()))
                .thenReturn(Optional.of(new Supplier(request.getSupplierId(), "Fornecedor")));


        Mockito.when(documentTypeRepository.findById(request.getCategoryId()))
                .thenReturn(Optional.of(new DocumentType(request.getDocumentTypeId(), "Boleto")));

        var bill = billService.store(request);
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

        var request = new BillRequestUpdateForTest();


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

        Mockito.when(supplierRepository.findById(request.getCategoryId()))
                .thenReturn(Optional.of(new Supplier(request.getSupplierId(), "Fornecedor")));


        Mockito.when(documentTypeRepository.findById(request.getCategoryId()))
                .thenReturn(Optional.of(new DocumentType(request.getDocumentTypeId(), "Boleto")));


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
