package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeNotFoundException;
import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.supplier.SupplierNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BillService {

    Bill create(BillCreateRequestDTO request) throws CategoryNotFoundException, SupplierNotFoundException, DocumentTypeNotFoundException;

    Bill update(BillUpdateRequestDTO request) throws DomainException;

    Bill getByIdOrFail(Long billId) throws BillNotFoundException;

    Bill pay(Long billId, BigDecimal amount, LocalDate paidAt);

}
