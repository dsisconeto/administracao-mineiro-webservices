package com.mercado.mineiro.administration.bill;


import lombok.NonNull;

public interface IBillUpdateRequest extends IBillStoreRequest {

    @NonNull
    Long getBillId();
}
