package com.mercado.mineiro.administration.app.bill;

import com.mercado.mineiro.administration.bill.IBillStoreRequest;
import com.mercado.mineiro.administration.bill.IBillUpdateRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRequestDTO extends StoreRequestDTO implements IBillUpdateRequest {

    private Long billId;
}
