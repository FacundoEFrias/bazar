package com.facufrias.bazar.service;


import com.facufrias.bazar.model.Cliente;

import java.util.List;

public interface IClienteService {

    //Crear un Cliente nuevo
    void createCliente(Cliente cliente);
    //Traer todos los clientes
    List<Cliente> getClientes();
    //Traer un Cliente por ID
    Cliente getClienteById(Long idCliente);
    //Eliminar un Cliente por ID
    void deleteClienteById(Long idCliente);
    //Editar un Cliente
    void editCliente(Long idCliente , Cliente clienteActualizado);

}
