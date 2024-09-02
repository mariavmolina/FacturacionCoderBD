package edu.coder.FacturacionSegundaEntregaMolina.service;

import edu.coder.FacturacionSegundaEntregaMolina.Model.Cliente;
import edu.coder.FacturacionSegundaEntregaMolina.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClienteService {
    private static final Logger logger =Logger.getLogger(ClienteService.class.getName());

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente agregarCliente(Cliente cliente){
        validarCliente(cliente);
        try {
            Cliente nuevoCliente = clienteRepository.save(cliente);
            logger.info("Cliente guardado exitosamente" + nuevoCliente.getId());
            return nuevoCliente;

        } catch (Exception e) {
            logger.severe("Error al guardar el cliente: " + e.getMessage());
            throw new RuntimeException("Error al guardar el cliente.");
        }

    }

    public Optional<Cliente> buscarCliente(Long id) {
        if (id == null || id <= 0) {
            logger.warning("ID inválido para búsqueda de cliente.");
            throw new IllegalArgumentException("ID inválido.");
        }
        return clienteRepository.findById(id);
    }
    private void validarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
    }

}
