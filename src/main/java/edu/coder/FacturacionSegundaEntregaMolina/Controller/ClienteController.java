package edu.coder.FacturacionSegundaEntregaMolina.Controller;

import edu.coder.FacturacionSegundaEntregaMolina.Model.Cliente;
import edu.coder.FacturacionSegundaEntregaMolina.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/agregar")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
        Cliente nuevoCliente=clienteService.agregarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable long id) {
        return clienteService.buscarCliente(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }}