package com.cavillon.market.controller;

import com.cavillon.market.dto.ProductCreateDTO;
import com.cavillon.market.dto.ProductUpdateDTO;
import com.cavillon.market.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    private ResponseEntity<?> create (@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(productCreateDTO));
    }

    @GetMapping("/")
    private ResponseEntity<?> findAll () {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{term}")
    private ResponseEntity<?> findBy (@PathVariable String term) {
        return ResponseEntity.ok(productService.findProduct(term));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<?> update (@PathVariable String id, @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productUpdateDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> softDelete(@PathVariable String id) {
        productService.softDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
