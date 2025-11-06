package com.cavillon.market.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @PrePersist
    private void prePersist() {
        this.unitPrice = product.getSellPrice();
    }

    public BigDecimal getSubtotal () {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
