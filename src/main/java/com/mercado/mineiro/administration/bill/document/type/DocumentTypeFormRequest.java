package com.mercado.mineiro.administration.bill.document.type;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DocumentTypeFormRequest {

    @NotBlank
    private String name;
}
