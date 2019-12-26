package com.mercado.mineiro.administration.bill.document.type;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private DocumentTypeRepository documentTypeRepository;
    private ModelMapper modelMapper;

    DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, ModelMapper modelMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.modelMapper = modelMapper;
    }


    public DocumentType create(DocumentTypeFormRequest request) {

        var documentType = modelMapper.map(request, DocumentType.class);

        return documentTypeRepository.save(documentType);
    }

    public DocumentType update(Long id, DocumentTypeFormRequest request) {

        var documentType = getByIdOrFail(id);

        modelMapper.map(request, documentType);

        return documentTypeRepository.save(documentType);
    }

    public DocumentType getByIdOrFail(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(DocumentTypeNotFoundException::new);
    }

    @Override
    public void exitsByIdOrFail(Long documentTypeId) {
        if (documentTypeRepository.existsById(documentTypeId)) return;

        throw new DocumentTypeNotFoundException();

    }
}
