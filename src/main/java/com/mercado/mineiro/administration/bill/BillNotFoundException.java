package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.common.EntityNotFoundException;

public class BillNotFoundException extends EntityNotFoundException {
    BillNotFoundException() {
        super("Conta a pagar não encontrada");
    }
}
