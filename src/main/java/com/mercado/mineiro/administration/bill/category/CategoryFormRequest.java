package com.mercado.mineiro.administration.bill.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
class CategoryFormRequest {

    @NotBlank
    private String name;

}
