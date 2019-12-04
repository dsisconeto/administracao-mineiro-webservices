package com.mercado.mineiro.administration.bill;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
class BillRequestStoreForTest implements IBillStoreRequest {
    private String description;
    private BigDecimal amount;
    private LocalDate payIn;
    private Long categoryId;
    private Long supplierId;
    private String documentCode;
    private Long documentTypeId;
}
