package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.common.exception.EntityNotFoundException;

class BillNotFoundException extends EntityNotFoundException {
    BillNotFoundException() {
        super("Conta a pagar não encontrada");
    }
}
