package com.mercado.mineiro.administration.supplier;

import lombok.NonNull;


public interface SupplierService {

    @NonNull
    Supplier getByIdOrFail(Long supplierId) throws SupplierNotFoundException;


    void existsByIdOrFail(Long supplierId) throws SupplierNotFoundException;
}
