package com.facufrias.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProducto;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "La marca no puede estar vacia")
    private String marca;
    @NotNull(message = "El costo es obligatorio")
    @Min(value = 0, message = "El costo no puede ser negativo")
    private Double costo;
    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value=0, message = "La cantidad disponible no puede ser negativa")
    private Integer cantidadDisponible;
}
