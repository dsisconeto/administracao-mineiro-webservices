package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeNotFoundException;
import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.supplier.SupplierNotFoundException;

public interface BillService {

    Bill create(BillCreateRequestDTO request) throws CategoryNotFoundException, SupplierNotFoundException, DocumentTypeNotFoundException;

    Bill update(BillUpdateRequestDTO request) throws DomainException;

    Bill getByIdOrFail(Long billId) throws BillNotFoundException;

}
