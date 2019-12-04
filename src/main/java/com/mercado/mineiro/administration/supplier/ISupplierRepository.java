package com.mercado.mineiro.administration.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
}
