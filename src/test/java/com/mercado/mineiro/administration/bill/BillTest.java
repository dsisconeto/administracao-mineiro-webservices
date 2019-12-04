package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.common.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {


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


}
