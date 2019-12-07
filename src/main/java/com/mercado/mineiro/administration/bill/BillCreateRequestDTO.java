package com.mercado.mineiro.administration.bill;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BillCreateRequestDTO {

    @NotNull
    @Length(max = 100, min = 5)
    private String description;
    @NotNull
    @DecimalMin("1.00")
    private BigDecimal amount;
    @NotNull
    private LocalDate payIn;
    @NotNull
    private Long categoryId;
    private Long supplierId;
    private String documentCode;
    private Long documentTypeId;

}
