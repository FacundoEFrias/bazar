package com.facufrias.bazar.repository;

import com.facufrias.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByFechaVenta(LocalDate fechaVenta);
}
