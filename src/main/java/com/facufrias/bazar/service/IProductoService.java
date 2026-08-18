package com.facufrias.bazar.service;

import com.facufrias.bazar.dto.ProductoDTO;
import com.facufrias.bazar.model.Producto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductoService {

    //Crear un producto nuevo
    void createProducto(Producto producto);
    //Lista de todos los productos
    List<Producto> getProductos();
    //Traer un Producto en particular por ID
    Producto getProductoById(Long codigoProducto);
    //Eliminar un Producto por ID
    void deleteProductoById(Long codigoProducto);
    //Editar un producto por ID
    void editProductoById(Long codigoProducto, Producto productoActualizado);
    //Traer todos los Productos que cuya cantidad sea menor a 5
    List<Producto> getProductosByCantidadDisponibleLessThan(Integer cantidadDisponible);

    List<ProductoDTO> searchProductos(String nombre, Double precioMinimo, Double precioMaximo);

    void importarProductosExternos();


}
