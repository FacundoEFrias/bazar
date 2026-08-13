package com.facufrias.bazar.service;

import com.facufrias.bazar.dto.ProductoDTO;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public void createProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long codigoProducto) {
        return productoRepository.findById(codigoProducto).orElse(null);
    }

    @Override
    public void deleteProductoById(Long codigoProducto) {
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    public void editProductoById(Long codigoProducto, Producto productoActualizado) {
                Producto prod = this.getProductoById(codigoProducto);
                if(prod != null){
                    if(productoActualizado.getNombre() != null){
                        prod.setNombre(productoActualizado.getNombre());
                    }
                    if(productoActualizado.getMarca() != null){
                        prod.setMarca(productoActualizado.getMarca());
                    }
                    if(productoActualizado.getCosto() != null){
                        prod.setCosto(productoActualizado.getCosto());
                    }
                    if(productoActualizado.getCantidadDisponible() != null){
                        prod.setCantidadDisponible(productoActualizado.getCantidadDisponible());
                    }

                    productoRepository.save(prod);
                }

    }

    @Override
    public List<Producto> getProductosByCantidadDisponibleLessThan(Integer cantidadDisponible) {
            List<Producto> listaproductos = this.getProductos();
            List<Producto> listaProductosActualizados = new ArrayList<>();
            for(Producto p : listaproductos){
                if(p.getCantidadDisponible() < cantidadDisponible){
                    listaProductosActualizados.add(p);
                }
            }
            return listaProductosActualizados;
    }

    @Override
    public List<ProductoDTO> searchProductos(String nombre, Double precioMinimo, Double precioMaximo) {
        List<ProductoDTO> listaProductosDTO = new ArrayList<>();
        List<Producto> productosEncontrados;
        if(nombre != null && !nombre.isBlank() && precioMinimo != null && precioMaximo != null){
            productosEncontrados = productoRepository.findByNombreContainingIgnoreCaseAndCostoBetween(nombre, precioMinimo, precioMaximo);
        } else if (nombre != null && !nombre.isBlank()) {
            productosEncontrados = productoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (precioMinimo != null && precioMaximo != null) {
            productosEncontrados = productoRepository.findByCostoBetween(precioMinimo, precioMaximo);

        }
        else {
            productosEncontrados = productoRepository.findAll();
        }
        for(Producto pro : productosEncontrados){
           ProductoDTO dto = new ProductoDTO(pro.getNombre(), pro.getMarca(), pro.getCosto());
           listaProductosDTO.add(dto);
        }
        return listaProductosDTO;

    }
    }
