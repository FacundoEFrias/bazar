package com.facufrias.bazar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private String nombre;
    private String marca;
    private Double costo;
}
