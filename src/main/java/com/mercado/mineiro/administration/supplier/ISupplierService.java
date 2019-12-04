package com.mercado.mineiro.administration.supplier;

import lombok.NonNull;


public interface ISupplierService {
    @NonNull
    Supplier getByIdOrFail(Long supplierId) throws SupplierNotFoundException;
}
