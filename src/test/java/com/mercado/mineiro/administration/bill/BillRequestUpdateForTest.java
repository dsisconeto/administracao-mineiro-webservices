package com.mercado.mineiro.administration.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
class BillRequestUpdateForTest extends BillRequestStoreForTest implements IBillUpdateRequest {
    private Long billId;
    private String description;
    private BigDecimal amount;
    private LocalDate payIn;
    private Long categoryId;
    private Long supplierId;
    private String documentCode;
    private Long documentTypeId;
}
