package com.mercado.mineiro.administration.bill;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class ChangeStatusEventHandler {

    @Async
    @TransactionalEventListener
    public void handler(BillChangeStatusEvent billChangeStatusEvent) {


        System.out.println(billChangeStatusEvent.getOldStatus());

    }
}
