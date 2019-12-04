package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.category.ICategoryService;
import com.mercado.mineiro.administration.bill.document.Document;
import com.mercado.mineiro.administration.bill.document.type.IDocumentTypeRepository;
import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.supplier.ISupplierRepository;
import com.mercado.mineiro.administration.supplier.Supplier;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {

    private final IBillRepository _billRepository;
    private ICategoryService _categoryService;
    private IDocumentTypeRepository _documentTypeRepository;
    private ISupplierRepository _supplierRepository;

    public BillService(
            IBillRepository billRepository,
            ICategoryService categoryService,
            IDocumentTypeRepository documentTypeRepository,
            ISupplierRepository supplierRepository
    ) {
        _billRepository = billRepository;
        _categoryService = categoryService;
        _documentTypeRepository = documentTypeRepository;

        _supplierRepository = supplierRepository;
    }

    public Bill store(IBillStoreRequest request) throws CategoryNotFoundException {

        var category = _categoryService.getByIdOrFail(request.getCategoryId());


        var bill = new Bill(
                request.getDescription(),
                request.getAmount(),
                category,
                request.getPayIn()
        );

        bill.setDocument(makeDocument(request));
        bill.setSupplier(makeSupplier(request));

        _billRepository.save(bill);

        return bill;
    }

    public Bill update(IBillUpdateRequest request) throws DomainException {

        var bill = getByIdOrFail(request.getBillId());
        var category = _categoryService.getByIdOrFail(request.getCategoryId());

        bill.setDescription(request.getDescription());
        bill.setAmount(request.getAmount());
        bill.setCategory(category);
        bill.setCategory(category);
        bill.setPayIn(request.getPayIn());
        bill.setDocument(makeDocument(request));
        bill.setSupplier(makeSupplier(request));

        _billRepository.save(bill);

        return bill;
    }


    public Bill getByIdOrFail(Long billId) throws BillNotFoundException {

        var bill = _billRepository.findById(billId);

        return bill.orElseThrow(BillNotFoundException::new);
    }


    private Document makeDocument(IBillStoreRequest request) {

        if (request.getDocumentCode() == null) return null;

        var documentType = _documentTypeRepository.findById(request.getDocumentTypeId());

        if (documentType.isEmpty()) return null;

        return new Document(request.getDocumentCode(), documentType.get());
    }

    private Supplier makeSupplier(IBillStoreRequest request) {

        if (request.getSupplierId() == null) return null;

        return _supplierRepository.findById(request.getSupplierId())
                .orElse(null);
    }


}
