package com.mercado.mineiro.administration.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IBillRepository extends JpaRepository<Bill, Long> {


}
