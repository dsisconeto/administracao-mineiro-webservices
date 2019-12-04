package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.common.DomainException;

public interface IBillService {

    Bill store(IBillStoreRequest request) throws CategoryNotFoundException;

    Bill update(IBillUpdateRequest request) throws DomainException;

    Bill getByIdOrFail(Long billId) throws BillNotFoundException;

}
