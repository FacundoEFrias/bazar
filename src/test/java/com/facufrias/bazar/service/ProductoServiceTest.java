package com.facufrias.bazar.service;

import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.repository.IProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private IProductoRepository productoRepository; // Repositorio simulado (Mock)

    @InjectMocks
    private ProductoService productoService; // Servicio real con el mock inyectado

    private Producto producto;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de cada test para preparar un objeto Producto de prueba
        producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setNombre("Teclado Mecánico");
        producto.setMarca("Logitech");
        producto.setCosto(50.0);
        producto.setCantidadDisponible(3); // Stock bajo para probar
    }

    @Test
    @DisplayName("Debería retornar el Producto cuando existe el ID")
    void getProductoById_Exitoso() {
        // 1. GIVEN
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // 2. WHEN (Llamamos a tu método real)
        Producto resultado = productoService.getProductoById(1L);

        // 3. THEN
        assertNotNull(resultado, "El producto no debería ser nulo");
        assertEquals("Teclado Mecánico", resultado.getNombre());
        assertEquals(50.0, resultado.getCosto());
        assertEquals(1L, resultado.getCodigoProducto());

        // Verificamos que se llamó al repositorio 1 sola vez
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debería llamar al repository.save() al crear un producto")
    void createProducto_Exitoso() {
        // WHEN
        productoService.createProducto(producto);

        // THEN
        // Verificamos que el método save del repositorio haya sido ejecutado con nuestro producto
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    @DisplayName("Debería filtrar correctamente los productos con stock menor al límite")
    void getProductosByCantidadDisponibleLessThan_Exitoso() {
        // GIVEN
        Producto producto2 = new Producto();
        producto2.setCodigoProducto(2L);
        producto2.setNombre("Mouse Óptico");
        producto2.setCantidadDisponible(10); // Este NO debería entrar en el filtro (< 5)

        when(productoRepository.findAll()).thenReturn(List.of(producto, producto2));

        // WHEN (Buscamos productos con menos de 5 unidades)
        List<Producto> resultado = productoService.getProductosByCantidadDisponibleLessThan(5);

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size(), "Debería haber solo 1 producto con stock menor a 5");
        assertEquals("Teclado Mecánico", resultado.get(0).getNombre());
    }
    @Test
    void searchProductos(){
        //GIVEN
        when(productoRepository.findByNombreContainingIgnoreCase("Teclado")).thenReturn(List.of(producto));
        //WHEN
        var producto = productoService.searchProductos("Teclado", null , null);
        //THEN
        assertNotNull(producto);
        assertEquals("Teclado Mecánico", producto.get(0).getNombre());
        assertEquals(1, producto.size());
        verify(productoRepository).findByNombreContainingIgnoreCase("Teclado");
    }
    @Test
    void deleteProductoById(){
        //GIVEN
        Long id = 1L;

        productoService.deleteProductoById(id);

        verify(productoRepository, times(1)).deleteById(id);
    }
    @Test
    void editProductoById(){
        //GIVEN
        Long id = 1L;
        Producto productoActualizado = new Producto();
        productoActualizado.setNombre("Teclado Inalámbrico");
        productoActualizado.setMarca("Razer");
        productoActualizado.setCosto(80.0);
        productoActualizado.setCantidadDisponible(5);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        //WHEN
        productoService.editProductoById(id, productoActualizado);

        //THEN
        verify(productoRepository, times(1)).findById(id);
        verify(productoRepository, times(1)).save(any(Producto.class));

        assertEquals("Teclado Inalámbrico", producto.getNombre());
        assertEquals("Razer", producto.getMarca());
        assertEquals(80.0, producto.getCosto());
    }
}