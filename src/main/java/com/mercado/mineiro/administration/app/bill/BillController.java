package com.mercado.mineiro.administration.app.bill;

import com.mercado.mineiro.administration.bill.Bill;
import com.mercado.mineiro.administration.bill.BillNotFoundException;
import com.mercado.mineiro.administration.bill.IBillRepository;
import com.mercado.mineiro.administration.bill.IBillService;
import com.mercado.mineiro.administration.bill.category.CategoryNotFoundException;
import com.mercado.mineiro.administration.common.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("bills")
public class BillController {


    private IBillService _billService;
    private IBillRepository _billRepository;

    BillController(IBillService billService, IBillRepository billRepository) {
        _billService = billService;
        _billRepository = billRepository;
    }

    @GetMapping
    public Page<Bill> list(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {


        return _billRepository.findAll(pageable);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bill> details(@PathVariable Long id) {
        try {
            var bill = _billService.getByIdOrFail(id);
            return ResponseEntity.ok(bill);
        } catch (BillNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Bill> store(
            @RequestBody @Valid StoreRequestDTO request,
            UriComponentsBuilder uriBuilder
    ) throws CategoryNotFoundException {
        var bill = _billService.store(request);

        var uri = uriBuilder.path("/bills/{id}").buildAndExpand(bill.getId()).toUri();

        return ResponseEntity.created(uri).body(bill);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestDTO request
    ) throws DomainException {

        request.setBillId(id);

        try {
            _billService.update(request);
            return ResponseEntity.noContent().build();
        } catch (BillNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
