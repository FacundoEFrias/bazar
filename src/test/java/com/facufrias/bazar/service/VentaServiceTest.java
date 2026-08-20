package com.facufrias.bazar.service;

import com.facufrias.bazar.dto.VentaDiaDTO;
import com.facufrias.bazar.dto.VentaMayorVentaDTO;
import com.facufrias.bazar.exception.ResourceNotFoundException;
import com.facufrias.bazar.model.Cliente;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.model.Venta;
import com.facufrias.bazar.repository.IVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private IVentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Venta venta1;
    private Venta venta2;
    private LocalDate fechaPrueba;

    @BeforeEach
    void setUp() {
        fechaPrueba = LocalDate.of(2026, 6, 15);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Ana");
        cliente.setApellido("Pérez");

        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setNombre("Cafetera");
        producto.setCosto(150.0);

        venta1 = new Venta();
        venta1.setCodigoVenta(1L);
        venta1.setFechaVenta(fechaPrueba);
        venta1.setTotal(300.0);
        venta1.setUnCliente(cliente);
        venta1.setListaProductos(List.of(producto));

        venta2 = new Venta();
        venta2.setCodigoVenta(2L);
        venta2.setFechaVenta(fechaPrueba);
        venta2.setTotal(500.0); // Esta es la mayor venta
        venta2.setUnCliente(cliente);
        venta2.setListaProductos(List.of(producto, producto));
    }

    @Test
    void getVentas(){
        when(ventaRepository.findAll()).thenReturn(List.of(venta1,venta2));

        List<Venta> resultado = ventaService.getVentas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(ventaRepository, times(1)).findAll();
    }
    @Test
    void getVentaById() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta1));

        Venta resutaldo = ventaService.getVentaById(1L);

        assertNotNull(resutaldo);
        verify(ventaRepository, times(1)).findById(1L);

    }
    @Test
    void getVentaById_NoExitoso(){
        Long id= 1L;
        when(ventaRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->{
            ventaService.getVentaById(id);
        });
        assertEquals("Venta no encontrado con el ID: 1", exception.getMessage());
        verify(ventaRepository, times(1)).findById(id);
    }
    @Test
    void createVenta(){
        ventaService.createVenta(venta1);

        verify(ventaRepository, times(1)).save(venta1);
    }
    @Test
    void deleteVenta(){
        ventaService.deleteVentaById(1L);

        verify(ventaRepository, times(1)).deleteById(1L);
    }
    @Test
    void deleteVenta_NoExitoso(){
        Long id = 1L;
        when(ventaRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, ()->
        {
            ventaService.deleteVentaById(id);
        });
        assertEquals("No se puede eliminar. Venta no encontrado con ID: 1", exception.getMessage());
        verify(ventaRepository, times(1)).existsById(id);
    }
    @Test
    void editVenta(){
        Long id = 1L;
        Venta ventaActualizada = new Venta();
        ventaActualizada.setFechaVenta(fechaPrueba);
        ventaActualizada.setTotal(400.0); // Esta es la mayor venta
        ventaActualizada.setUnCliente(null);
        ventaActualizada.setListaProductos(List.of());

        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta1));

        ventaService.editVentaById(id, ventaActualizada);

        verify(ventaRepository, times(1)).findById(id);
        verify(ventaRepository, times(1)).save(venta1);

        assertEquals(400.0, venta1.getTotal());
    }
    @Test
    void getProductosByVenta(){
        Long id = 1L;
        when(ventaRepository.findById(id)).thenReturn(Optional.of(venta1));

        List<Producto> resultado = ventaService.getProductosByVenta(id);

        assertNotNull(resultado);
        assertEquals("Cafetera", resultado.get(0).getNombre());
        assertEquals(1, resultado.size());

    }
    @Test
    void getVentasByFecha(){
        // GIVEN
        LocalDate fechaBuscada = LocalDate.of(2026, 6, 15);
        LocalDate otraFecha = LocalDate.of(2026, 6, 20);

        Venta ventaDiferente = new Venta();
        ventaDiferente.setCodigoVenta(3L);
        ventaDiferente.setFechaVenta(otraFecha);
        when(ventaRepository.findAll()).thenReturn(List.of(venta1,venta2,ventaDiferente));

        List<Venta> resultado = ventaService.getVentasByFecha(fechaBuscada);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(ventaRepository,times(1)).findAll();
    }
    @Test
    void getSumAndCountVentasByFecha(){
        when(ventaRepository.findAll()).thenReturn(List.of(venta1,venta2));

        VentaDiaDTO resultado = ventaService.getSumAndCountVentasByFecha(fechaPrueba);

        assertNotNull(resultado);
        assertEquals(800.0, resultado.getMontoTotal());
        assertEquals(2, resultado.getCantidadVentas());

    }
    @Test
    void getMayorVenta(){
        when(ventaRepository.findAll()).thenReturn(List.of(venta1,venta2));

        VentaMayorVentaDTO resultado = ventaService.getMayorVenta();

        assertNotNull(resultado);
        assertEquals(500.0, resultado.getTotal());
        assertEquals("Ana", resultado.getNombreCliente());
        assertEquals(2, resultado.getCantidadProductos());
    }
}
