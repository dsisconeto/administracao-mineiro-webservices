package com.mercado.mineiro.administration.bill.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mercado.mineiro.administration.common.web.Responses.*;

@RestController
@RequestMapping(path = CategoryController.PATH)
public class CategoryController {
    static final String PATH = "/categories";
    private CategoryService categoryService;

    protected CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public Page<Category> findAllPaginate(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC)
                    Pageable pageable
    ) {
        return categoryService.findAllPaginate(pageable);
    }


    @PostMapping(path = "")
    public ResponseEntity<Category> create(
            @RequestBody @Valid CategoryFormRequest request
    ) {

        var category = categoryService.create(request);

        return created(PATH, category);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody @Valid CategoryFormRequest request
    ) {

        categoryService.update(id, request);

        return notContent();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity destroy(
            @PathVariable Long id
    ) {

        categoryService.delete(id);

        return notContent();
    }
}
