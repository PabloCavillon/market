package com.cavillon.market.service;

import com.cavillon.market.dto.SaleItemCreateDTO;
import com.cavillon.market.model.Product;
import com.cavillon.market.model.Sale;
import com.cavillon.market.model.SaleItem;
import com.cavillon.market.repository.SaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;
    private final ProductService productService;

    public SaleItem create (SaleItemCreateDTO saleItemCreateDTO) {
        Product product = productService.findProduct(saleItemCreateDTO.getProductId());

        SaleItem saleItem = new SaleItem();

        saleItem.setQuantity(saleItemCreateDTO.getQuantity());
        saleItem.setProduct(product);
        saleItem.setUnitPrice(product.getSellPrice());

        return saleItemRepository.save(saleItem);
    }

    public List<?> getAllBySale(String saleId) {
        return saleItemRepository.findBySaleId(saleId);
    }

}
