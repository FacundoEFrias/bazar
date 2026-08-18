package com.facufrias.bazar.service;

import com.facufrias.bazar.model.Cliente;
import com.facufrias.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;

    // Inyección por constructor (recomendado frente a @Autowired en campos)
    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    @Override
    public void deleteClienteById(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    @Override
    public void editCliente(Long idCliente, Cliente clienteActualizado) {
        Cliente cliente = this.getClienteById(idCliente);
        if(cliente != null){
            if(clienteActualizado.getNombre() != null){
                cliente.setNombre(clienteActualizado.getNombre());
            }
            if(clienteActualizado.getApellido() != null){
                cliente.setApellido(clienteActualizado.getApellido());
            }
            if(clienteActualizado.getDni() != null){
                cliente.setDni(clienteActualizado.getDni());
            }

            clienteRepository.save(cliente);
        }
    }
}
