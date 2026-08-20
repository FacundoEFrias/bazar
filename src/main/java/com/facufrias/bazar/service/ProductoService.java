package com.facufrias.bazar.service;

import com.facufrias.bazar.dto.ExternalProductDTO;
import com.facufrias.bazar.dto.ProductoDTO;
import com.facufrias.bazar.exception.ResourceNotFoundException;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private RestTemplate restTemplate;

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
        return productoRepository.findById(codigoProducto).orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el ID: " + codigoProducto));
    }

    @Override
    public void deleteProductoById(Long codigoProducto) {

        if(!productoRepository.existsById(codigoProducto)){
            throw new ResourceNotFoundException("Producto no se puede eliminar. Producto no encontrado con el ID: " + codigoProducto);
        }
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    public void editProductoById(Long codigoProducto, Producto productoActualizado) {
                Producto prod = this.getProductoById(codigoProducto);

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

    @Override
    public List<Producto> getProductosByCantidadDisponibleLessThan(Integer cantidadDisponible) {
            List<Producto> listaproductos = this.getProductos();
            return listaproductos.stream().filter(producto -> producto.getCantidadDisponible() < cantidadDisponible).toList();
            /*for(Producto p : listaproductos){
                if(p.getCantidadDisponible() < cantidadDisponible){
                    listaProductosActualizados.add(p);
                }
            }
            return listaProductosActualizados;*/
    }

    @Override
    public List<ProductoDTO> searchProductos(String nombre, Double precioMinimo, Double precioMaximo) {
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
        /*
        for(Producto pro : productosEncontrados){
//           ProductoDTO dto = new ProductoDTO(pro.getNombre(), pro.getMarca(), pro.getCosto());
           listaProductosDTO.add(dto);
        }*/
        return productosEncontrados.stream().map(producto -> new ProductoDTO(producto.getNombre(),producto.getMarca(),producto.getCosto())).toList();

    }

    @Override
    public void importarProductosExternos() {
        String url = "https://dummyjson.com/products";
        ExternalProductDTO respuesta = restTemplate.getForObject(url, ExternalProductDTO.class);

        if(respuesta != null && respuesta.getProducts() != null){
            for(ExternalProductDTO.ProductItemDTO item : respuesta.getProducts()){
                Producto producto = new Producto();
                producto.setNombre(item.getTitle());
                producto.setMarca(item.getBrand() != null ? item.getBrand() : "Sin marca");
                producto.setCosto(item.getPrice());
                producto.setCantidadDisponible(item.getStock());

                productoRepository.save(producto);
            }
        }
    }
}
