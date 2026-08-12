package com.facufrias.bazar.service;


import com.facufrias.bazar.dto.VentaDiaDTO;
import com.facufrias.bazar.dto.VentaMayorVentaDTO;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    //Crear un Venta nueva
    void createVenta(Venta venta);
    //Lista de todas los Ventas
    List<Venta> getVentas();
    //Traer una Venta en particular por ID
    Venta getVentaById(Long codigoVenta);
    //Eliminar una Venta por ID
    void deleteVentaById(Long codigoVenta);
    //Editar una Venta por ID
    void editVentaById(Long codigoVenta, Venta ventaActualizada);
    //Traer lista de Productos de una determinada venta
    List<Producto> getProductosByVenta(Long codigoVenta );
    //Traer todas las ventas de un determinado dia
    List<Venta> getVentasByFecha(LocalDate fechaVenta);
    //Traer la sumatoria del monto, cantidad total de ventas
    VentaDiaDTO getSumAndCountVentasByFecha(LocalDate fechaVenta);
    //Traer el cliente con el monto de venta mayor
    VentaMayorVentaDTO getMayorVenta();
}
