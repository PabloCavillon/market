package com.cavillon.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal sellPrice;

    @Column(precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal buyPrice;

    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean active;

    @JsonIgnore
    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagesJson;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> images;

    @PrePersist
    protected void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        active = true;
        serializeImages();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
        serializeImages();
    }

    private void serializeImages() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (images == null) images = List.of();
            imagesJson = mapper.writeValueAsString(images);
        } catch (Exception e) {
            imagesJson = "[]";
        }
    }

    @PostLoad
    protected void postLoad() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (imagesJson == null || imagesJson.isBlank() || imagesJson.equalsIgnoreCase("null")) {
                images = List.of();
            } else {
                images = mapper.readValue(imagesJson, new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {});
            }
        } catch (Exception e) {
            images = List.of();
        }
    }

}
