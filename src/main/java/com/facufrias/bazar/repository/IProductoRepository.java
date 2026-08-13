package com.facufrias.bazar.repository;

import com.facufrias.bazar.model.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCostoBetween(Double precioMinimo, Double precioMaximo);

    List<Producto> findByNombreContainingIgnoreCaseAndCostoBetween(String nombre, Double precioMinimo, Double precioMaximo);
}
