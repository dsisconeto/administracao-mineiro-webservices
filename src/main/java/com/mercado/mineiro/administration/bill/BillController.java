package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.bill.document.type.DocumentTypeNotFoundException;
import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.common.web.ControllerBase;
import com.mercado.mineiro.administration.common.web.Responses;
import com.mercado.mineiro.administration.supplier.SupplierNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static com.mercado.mineiro.administration.common.web.Responses.*;

@RestController
@RequestMapping(BillController.PATH)
public class BillController {
    final static String PATH = "bills";

    private BillService billService;
    private BillRepository billRepository;

    BillController(BillService billService, BillRepository billRepository) {
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
        return ok(bill);

    }


    @PostMapping
    public ResponseEntity create(
            @RequestBody @Valid BillCreateRequestDTO request
    ) {

        var bill = billService.create(request);

        return created(PATH, bill);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody @Valid BillUpdateRequestDTO request
    ) {

        request.setBillId(id);
        billService.update(request);
        return notContent();
    }


}
