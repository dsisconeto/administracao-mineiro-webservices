package com.mercado.mineiro.administration.app.bill;

import com.mercado.mineiro.administration.bill.IBillStoreRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class StoreRequestDTO implements IBillStoreRequest {
    @NotNull
    @Length(max = 100, min = 5)
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDate payIn;
    @NotNull
    private Long categoryId;
    private Long supplierId;
    private String documentCode;
    private Long documentTypeId;
}
