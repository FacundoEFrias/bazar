package com.facufrias.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @NotBlank(message = "El nombre debe ser obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido debe ser obligatorio")
    private String apellido;
    @NotBlank(message = "El dni debe ser obligatorio")
    private String dni;
}
