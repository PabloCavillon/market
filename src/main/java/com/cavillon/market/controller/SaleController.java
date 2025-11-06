package com.cavillon.market.controller;

import com.cavillon.market.dto.SaleCreateDTO;
import com.cavillon.market.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/{date}")
    public ResponseEntity<?> getSalesByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return ResponseEntity.ok(saleService.getSalesByDate(date));
    }

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleCreateDTO saleCreateDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleService.createSale(saleCreateDTO));
    }
}
