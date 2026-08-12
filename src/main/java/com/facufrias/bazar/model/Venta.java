package com.facufrias.bazar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoVenta;
    @NotNull(message = "La fecha de venta es obligatoria")
    private LocalDate fechaVenta;
    @NotNull(message = "El total es obligatorio")
    private Double total;
    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "fk_venta"),
            inverseJoinColumns = @JoinColumn(name = "fk_producto")
    )
    private List<Producto> listaProductos;

    @OneToOne
    @JoinColumn(name = "un_cliente_id_cliente", referencedColumnName = "idCliente")
    private Cliente unCliente;

}
