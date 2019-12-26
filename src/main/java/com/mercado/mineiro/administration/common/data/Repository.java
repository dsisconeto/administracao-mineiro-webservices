package com.mercado.mineiro.administration.common.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repository<T> extends JpaRepository<T, Long> {
}
