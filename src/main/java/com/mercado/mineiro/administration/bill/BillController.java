package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeNotFoundException;
import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.common.web.ControllerBase;
import com.mercado.mineiro.administration.common.web.ResponseFactory;
import com.mercado.mineiro.administration.supplier.SupplierNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("bills")
public class BillController extends ControllerBase {


    private BillService billService;
    private BillRepository billRepository;

    BillController(BillService billService, BillRepository billRepository, ResponseFactory responseFactory) {
        super(responseFactory);
        this.billService = billService;
        this.billRepository = billRepository;
    }

    @GetMapping
    public Page<Bill> list(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {


        return billRepository.findAll(pageable);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bill> details(@PathVariable Long id) throws BillNotFoundException {
        var bill = billService.getByIdOrFail(id);
        return response().ok(bill);

    }


    @PostMapping
    @Transactional
    public ResponseEntity create(
            @RequestBody @Valid BillCreateRequestDTO request
    ) throws SupplierNotFoundException, DocumentTypeNotFoundException, CategoryNotFoundException {


        var bill = billService.create(request);

        return response().created(
                "/bills/{id}",
                bill.getId(),
                bill
        );


    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody @Valid BillUpdateRequestDTO request
    ) throws DomainException {

        request.setBillId(id);


        billService.update(request);
        return response().notContent();
    }


}
