package com.facufrias.bazar.controller;

import com.facufrias.bazar.dto.VentaDiaDTO;
import com.facufrias.bazar.dto.VentaMayorVentaDTO;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.model.Venta;
import com.facufrias.bazar.service.IVentaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@Validated
public class VentaController {
    @Autowired
    private IVentaService ventaService;

    @PostMapping("/crear")
    public ResponseEntity<String> createVenta(@Valid @RequestBody Venta venta){
        ventaService.createVenta(venta);
        return new ResponseEntity<>("Venta creada con exito", HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Venta>> getVentas(){
        return new ResponseEntity<>(ventaService.getVentas(),HttpStatus.OK);
    }
    @GetMapping("/{codigoVenta}")
    public ResponseEntity<Venta> getVentaById(@PathVariable
                                                  @Min(value = 1, message = "El codigo venta debe ser mayor a 0")
                                                  Long codigoVenta){
        return new ResponseEntity<>(ventaService.getVentaById(codigoVenta),HttpStatus.OK);
    }
    @GetMapping("/productos/{codigoVenta}")
    public ResponseEntity<List<Producto>> getProductosByVenta(@PathVariable
                                                                  @Min(value = 1, message = "El codigo venta debe ser mayor a 0")
                                                                  Long codigoVenta ){
        return new ResponseEntity<>(ventaService.getProductosByVenta(codigoVenta), HttpStatus.OK);
    }
    @GetMapping("/fecha/{fechaVenta}")
    public ResponseEntity<List<Venta>> getVentasByFecha(@PathVariable
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            LocalDate fechaVenta){
        return new ResponseEntity<>(ventaService.getVentasByFecha(fechaVenta), HttpStatus.OK);
    }
    @GetMapping("/monto/cantidadtotal/{fechaVenta}")
    public ResponseEntity<VentaDiaDTO> getSumAndCountVentasByFecha(@PathVariable
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                       LocalDate fechaVenta){
        return new ResponseEntity<>(ventaService.getSumAndCountVentasByFecha(fechaVenta),HttpStatus.OK);
    }
    @GetMapping("/mayorventa")
    public ResponseEntity<VentaMayorVentaDTO> getMayorVenta(){
        return new ResponseEntity<>(ventaService.getMayorVenta(), HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{codigoVenta}")
    public ResponseEntity<String> deleteVentaById(@PathVariable
                                                      @Min(value = 1, message = "El codigo venta debe ser mayor a 0")
                                                      Long codigoVenta){
        ventaService.deleteVentaById(codigoVenta);
        return new ResponseEntity<>("Venta eliminada con exito", HttpStatus.OK);
    }
    @PutMapping("/editar/{codigoVenta}")
    public ResponseEntity<String> editVentaById(@PathVariable
                                                    @Min(value = 1, message = "El codigo venta debe ser mayor a 0")
                                                    Long codigoVenta,
                                                @Valid
                                                @RequestBody Venta ventaActualizada){
        ventaService.editVentaById(codigoVenta,ventaActualizada);
        return new ResponseEntity<>("Venta editada con exito", HttpStatus.OK);
    }
}
