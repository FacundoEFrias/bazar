package com.facufrias.bazar.controller;

import com.facufrias.bazar.model.Cliente;
import com.facufrias.bazar.service.IClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@Valid @RequestBody Cliente cliente){
        clienteService.createCliente(cliente);
        return new ResponseEntity<>("Cliente creado con éxito", HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Cliente>>  getClientes(){
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable
                                                      @Min(value = 1, message = "El idCliente debe ser mayor a 0")
                                                      Long idCliente){
        return new ResponseEntity<>(clienteService.getClienteById(idCliente),HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity<String> deleteClienteById(@PathVariable
                                                        @Min(value = 1, message = "El idCliente debe ser mayor a 0")
                                                        Long idCliente){
        clienteService.deleteClienteById(idCliente);
        return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
    }
    @PutMapping("/editar/{idCliente}")
    public ResponseEntity<String> editCliente(@PathVariable
                                                  @Min(value = 1,message = "El idCliente debe ser mayor a 0")
                                                  Long idCliente,
                                              @Valid
                                              @RequestBody Cliente cliente){
        clienteService.editCliente(idCliente,cliente);
        return new ResponseEntity<>("Cliente editado con exito", HttpStatus.OK);
    }


}
