package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.category.CategoryService;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeNotFoundException;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeService;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.supplier.SupplierNotFoundException;
import com.mercado.mineiro.administration.supplier.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BillServiceImp implements BillService {

    private final BillRepository billRepository;
    private CategoryService categoryService;
    private SupplierService supplierService;
    private DocumentTypeService documentTypeService;
    private ModelMapper modelMapper;


    public BillServiceImp(
            BillRepository billRepository,
            CategoryService categoryService,
            SupplierService supplierService,
            DocumentTypeService documentTypeService,
            ModelMapper modelMapper
    ) {
        this.billRepository = billRepository;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
        this.documentTypeService = documentTypeService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Bill create(BillCreateRequestDTO request) throws CategoryNotFoundException, SupplierNotFoundException, DocumentTypeNotFoundException {

        categoryService.existsByIdOrFail(request.getCategoryId());

        if (request.getSupplierId() != null) {
            supplierService.existsByIdOrFail(request.getSupplierId());
        }

        if (request.getDocumentCode() != null) {
            documentTypeService.exitsByIdOrFail(request.getDocumentTypeId());
        }
        var bill = modelMapper.map(request, Bill.class);

        billRepository.save(bill);

        return bill;
    }

    public Bill update(BillUpdateRequestDTO request) throws DomainException {
        var bill = getByIdOrFail(request.getBillId());

        categoryService.existsByIdOrFail(request.getCategoryId());

        if (request.getSupplierId() != null) {
            supplierService.existsByIdOrFail(request.getSupplierId());
        }

        modelMapper.map(request, bill);

        billRepository.save(bill);

        return bill;
    }


    public Bill getByIdOrFail(Long billId) {

        var bill = billRepository.findById(billId);

        return bill.orElseThrow(BillNotFoundException::new);
    }


    public Bill pay(Long billId, BigDecimal amount, LocalDate paidAt) {

        var bill = billRepository.findById(billId)
                .orElseThrow(BillNotFoundException::new);

        bill.pay(new Payment(amount, paidAt));

        return bill;
    }

}
