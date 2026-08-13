package com.facufrias.bazar.controller;

import com.facufrias.bazar.dto.ProductoDTO;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.service.IProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductosController {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<String> createProducto(@Valid @RequestBody Producto producto){
        productoService.createProducto(producto);
        return new ResponseEntity<>("Producto creado con exito", HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Producto>> getProductos(){
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }
    @GetMapping("/{codigoProducto}")
    public ResponseEntity<Producto> getProductoById(@PathVariable
                                                        @Min(value = 1, message = "El codigo producto debe ser mayor a 0")
                                                        Long codigoProducto){
        return new ResponseEntity<>(productoService.getProductoById(codigoProducto), HttpStatus.OK);
    }
    @GetMapping("/faltastock")
    public ResponseEntity<List<Producto>> getProductosFaltaStock(){
        return new ResponseEntity<>(productoService.getProductosByCantidadDisponibleLessThan(5),HttpStatus.OK);
    }
    //Si preferís que sea dinámico por URL, usás esta variante:
    @GetMapping("/faltastock/{cantidadDisponible}")
    public ResponseEntity<List<Producto>> getProductosByCantidadDisponibleLessThan(@PathVariable
                                                                                       @Min(value = 1, message = "Cantidad Disponible debe ser mayor a 0")
                                                                                       Integer cantidadDisponible) {
        return new ResponseEntity<>(productoService.getProductosByCantidadDisponibleLessThan(cantidadDisponible), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> searchProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precioMinimo,
            @RequestParam(required = false) Double precioMaximo
    ){
        return new ResponseEntity<>(productoService.searchProductos(nombre, precioMinimo, precioMaximo),HttpStatus.OK);
    }


    @DeleteMapping("/eliminar/{codigoProducto}")
    public ResponseEntity<String> deleteProductoById(@PathVariable
                                                         @Min(value = 1, message = "El codigo producto debe ser mayor a 0")
                                                         Long codigoProducto){
        productoService.deleteProductoById(codigoProducto);
        return new ResponseEntity<>("Producto borrado con exito", HttpStatus.OK);
    }
    @PutMapping("/editar/{codigoProducto}")
    public ResponseEntity<String> editProductoById(@PathVariable
                                                       @Min(value = 1, message = "El codigo producto debe ser mayor a 0")
                                                       Long codigoProducto,
                                                        @Valid
                                                         @RequestBody Producto producto){
        productoService.editProductoById(codigoProducto,producto);
        return new ResponseEntity<>("El producto fue editado con exito", HttpStatus.OK);
    }


}
