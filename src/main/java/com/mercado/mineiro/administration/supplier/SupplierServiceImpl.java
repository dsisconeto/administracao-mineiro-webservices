package com.mercado.mineiro.administration.supplier;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {


    private SupplierRepository supplierRepository;

    SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public @NonNull Supplier getByIdOrFail(Long supplierId) throws SupplierNotFoundException {
        return supplierRepository.findById(supplierId)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public void existsByIdOrFail(Long supplierId) throws SupplierNotFoundException {
        if (supplierRepository.existsById(supplierId)) return;
        throw new SupplierNotFoundException();
    }
}
