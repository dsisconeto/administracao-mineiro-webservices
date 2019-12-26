package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.common.base.DomainEvent;
import lombok.Getter;

public class BillChangeStatusEvent extends DomainEvent {

    @Getter
    private Bill bill;
    @Getter
    private final Status oldStatus;

    public BillChangeStatusEvent(Bill bill, Status oldStatus) {
        this.bill = bill;
        this.oldStatus = oldStatus;
    }
}
