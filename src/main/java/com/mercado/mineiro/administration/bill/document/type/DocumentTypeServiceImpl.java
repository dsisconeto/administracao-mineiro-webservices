package com.mercado.mineiro.administration.bill.document.type;

import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private DocumentTypeRepository documentTypeRepository;

    DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public void exitsByIdOrFail(Long documentTypeId) throws DocumentTypeNotFoundException {
        if (documentTypeRepository.existsById(documentTypeId)) return;

        throw new DocumentTypeNotFoundException();

    }
}
