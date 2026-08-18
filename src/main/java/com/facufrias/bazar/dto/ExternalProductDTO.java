package com.facufrias.bazar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ExternalProductDTO {
    private List<ProductItemDTO> products;

    @Getter @Setter
    public static class ProductItemDTO {
        private String title;
        private String brand;
        private Double price;
        private Integer stock;
    }
}
