package com.cavillon.market.repository;

import com.cavillon.market.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, String> {
    List<SaleItem> findBySaleId(String saleId);
}
