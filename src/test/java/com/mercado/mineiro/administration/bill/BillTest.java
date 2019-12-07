package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.common.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BillTest {

    @Autowired
    private ModelMapper modelMapper;


    @Test
    void pay() throws DomainException {

        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now()
        );

        var payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(70.30));

        bill.pay(payment);

        assertEquals(BigDecimal.valueOf(9.8), bill.getInterest());
        assertTrue(bill.isStatusPaid());

    }

    @Test
    void cannot_pay() {

        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now()
        );

        var payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(50.00));
        assertThrows(DomainException.class, () -> bill.pay(payment));
    }


    @Test
    void cancelled() {
        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now()
        );

        bill.cancel();
        assertTrue(bill.isStatusCanceled());
    }

    @Test
    void to_pay_today() {
        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now()
        );


        assertTrue(bill.isStatusToPayToday());

    }

    @Test
    void over_due() {
        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now().minusDays(1)
        );


        assertTrue(bill.isStatusToOverDue());

    }

    @Test
    void payable() {

        var bill = new Bill(
                "Gás",
                BigDecimal.valueOf(60.50),
                new Category(),
                LocalDate.now().plusDays(1)
        );

        assertTrue(bill.isStatusPayable());
    }


    @Test
    void mapper() {


        var dto = new BillCreateRequestDTO();

        dto.setAmount(BigDecimal.valueOf(10L));
        dto.setDescription("Olá mundo");
        dto.setPayIn(LocalDate.now().plusDays(1));
        dto.setCategoryId(1L);
        dto.setSupplierId(1L);
        dto.setDocumentCode("123465");
        dto.setDocumentTypeId(1L);


        var bill = modelMapper.map(dto, Bill.class);

        assertEquals(dto.getAmount(), bill.getAmount());
        assertEquals(dto.getDescription(), bill.getDescription());
        assertEquals(dto.getPayIn(), bill.getPayIn());
        assertEquals(dto.getCategoryId(), bill.getCategory().getId());
        assertEquals(dto.getSupplierId(), bill.getSupplier().getId());
        assertEquals(dto.getDocumentCode(), bill.getDocument().getCode());
        assertEquals(dto.getDocumentTypeId(), bill.getDocument().getType().getId());

    }

    @Test
    void document_is_null() {


        var dto = new BillCreateRequestDTO();

        dto.setAmount(BigDecimal.valueOf(10L));
        dto.setDescription("Olá mundo");
        dto.setPayIn(LocalDate.now().plusDays(1));
        dto.setCategoryId(1L);
        dto.setSupplierId(1L);


        var bill = modelMapper.map(dto, Bill.class);

        assertEquals(dto.getAmount(), bill.getAmount());
        assertEquals(dto.getDescription(), bill.getDescription());
        assertEquals(dto.getPayIn(), bill.getPayIn());
        assertEquals(dto.getCategoryId(), bill.getCategory().getId());
        assertEquals(dto.getSupplierId(), bill.getSupplier().getId());
        assertNull(bill.getDocument());


    }


    @Test
    void mapper_update() {


        var bill = new Bill(
                "Olá mundo",
                BigDecimal.valueOf(50),
                new Category(1L, "Padrão"),
                LocalDate.now()
        );


        var dto = new BillUpdateRequestDTO();

        dto.setAmount(BigDecimal.valueOf(10L));
        dto.setDescription("Olá mundo 2");
        dto.setPayIn(LocalDate.now());

        modelMapper.map(dto, bill);

        assertEquals(dto.getAmount(), bill.getAmount());
        assertEquals(dto.getDescription(), bill.getDescription());
        assertEquals(LocalDate.now(), bill.getPayIn());


    }
}
