package com.cavillon.market.controller;

import com.cavillon.market.service.SaleItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saleItem")
public class SaleItemController {

    private final SaleItemService saleItemService;

    @GetMapping("/{saleId}")
    public ResponseEntity<?> getBySaleId(@Valid @PathVariable String saleId) {
        return ResponseEntity.ok(saleItemService.getAllBySale(saleId));
    }

}
