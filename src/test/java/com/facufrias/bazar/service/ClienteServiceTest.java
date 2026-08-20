package com.facufrias.bazar.service;

import com.facufrias.bazar.exception.ResourceNotFoundException;
import com.facufrias.bazar.model.Cliente;
import com.facufrias.bazar.repository.IClienteRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Carlos");
        cliente.setApellido("Gómez");
        cliente.setDni("12345678");
    }
    @Test
    void getClientes_Exitoso(){
        //GIVEN
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.getClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Carlos", resultado.get(0).getNombre());
        verify(clienteRepository,times(1)).findAll();

    }
    @Test
    void getClienteById_Exitoso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.getClienteById(1L);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }
    @Test
    void getClienteById_NoEncontrado_DeberiaLanzarExcepcion() {
        Long idInexistente = 99L;

        // Simulamos que el repositorio devuelve vacío
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Verificamos que se lance la ResourceNotFoundException
        ResourceNotFoundException excepcion = assertThrows(ResourceNotFoundException.class, () -> {
            clienteService.getClienteById(idInexistente);
        });

        assertEquals("Cliente no encontrado con el ID: 99", excepcion.getMessage());
        verify(clienteRepository, times(1)).findById(idInexistente);
    }
    @Test
    void createCliente(){
        clienteService.createCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
        }
    @Test
    void deleteClienteById(){
        clienteService.deleteClienteById(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
    @Test
    void deleteClienteById_NoExitoso(){
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->{
            clienteService.deleteClienteById(id);
        });
        assertEquals("No se puede eliminar. Cliente no encontrado con el ID: 1", exception.getMessage());
        verify(clienteRepository, times(1)).existsById(id);

    }
}
