package com.cavillon.market.service;

import com.cavillon.market.dto.ProductCreateDTO;
import com.cavillon.market.dto.ProductUpdateDTO;
import com.cavillon.market.model.Product;
import com.cavillon.market.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create (ProductCreateDTO createDTO) {
        if (productRepository.existsByNameIgnoreCaseOrCodeIgnoreCase(createDTO.getName(), createDTO.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }

        Product product = new Product();
        product.setName(createDTO.getName());
        product.setCode(createDTO.getCode());
        product.setDescription(createDTO.getDescription());
        product.setSellPrice(createDTO.getSellPrice());
        product.setBuyPrice(createDTO.getBuyPrice());
        product.setStock(createDTO.getStock());

        return productRepository.save(product);
    }

    public Product findProduct(String term) {
        return productRepository.findByNameIgnoreCaseOrCodeIgnoreCaseOrIdIgnoreCase(term, term, term)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"
                ));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(String id, ProductUpdateDTO updateDTO) {
        Product product = this.findProduct(id);

        if (updateDTO.getDescription() != null) {
            product.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getSellPrice() != null) {
            if (updateDTO.getSellPrice().signum() < 0)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sellPrice cannot be negative");
            product.setSellPrice(updateDTO.getSellPrice());
        }
        if (updateDTO.getBuyPrice() != null) {
            if (updateDTO.getBuyPrice().signum() < 0)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "buyPrice cannot be negative");
            product.setBuyPrice(updateDTO.getBuyPrice());
        }
        if (updateDTO.getStock() != null) {
            if (updateDTO.getStock() < 0)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "stock cannot be negative");
            product.setStock(updateDTO.getStock());
        }

        return product;
    }


    @Transactional
    public void softDelete(String id) {
        Product product = this.findProduct(id);
        product.setActive(false);
    }


}
