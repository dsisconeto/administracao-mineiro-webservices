package com.mercado.mineiro.administration.supplier;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {


    private ISupplierRepository _supplierRepository;

    SupplierService(ISupplierRepository supplierRepository) {
        _supplierRepository = supplierRepository;
    }


    @Override
    public @NonNull Supplier getByIdOrFail(Long supplierId) throws SupplierNotFoundException {
        return _supplierRepository.findById(supplierId)
                .orElseThrow(SupplierNotFoundException::new);
    }
}
