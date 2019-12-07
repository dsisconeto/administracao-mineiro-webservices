package com.mercado.mineiro.administration.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
