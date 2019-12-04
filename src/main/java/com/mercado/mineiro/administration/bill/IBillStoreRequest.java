package com.mercado.mineiro.administration.bill;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IBillStoreRequest {

    String getDescription();

    BigDecimal getAmount();

    LocalDate getPayIn();

    Long getCategoryId();

    Long getSupplierId();


    String getDocumentCode();

    Long getDocumentTypeId();
}
