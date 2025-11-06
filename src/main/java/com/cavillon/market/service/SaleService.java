package com.cavillon.market.service;

import com.cavillon.market.dto.SaleCreateDTO;
import com.cavillon.market.model.Sale;
import com.cavillon.market.model.SaleItem;
import com.cavillon.market.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;

    public List<Sale> getSalesByDate(LocalDate date){
        return saleRepository.findByDate(date);
    }

    public Sale createSale(SaleCreateDTO saleCreateDTO) {
        List<SaleItem> items = new ArrayList<>();
        saleCreateDTO.getItems().forEach(item -> {
            items.add(saleItemService.create(item));
        });

        Sale sale = new Sale();
        sale.setItems(items);
        sale.setDollarValue(saleCreateDTO.getDollarValue());

        return saleRepository.save(sale);
    }

    public Sale getById (String id) {
        return saleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
    }
}
