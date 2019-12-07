package com.mercado.mineiro.administration.bill.document.type;

public interface DocumentTypeService {

    void exitsByIdOrFail(Long documentTypeId) throws DocumentTypeNotFoundException;

}
