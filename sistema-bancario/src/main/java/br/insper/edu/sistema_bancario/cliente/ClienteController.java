package br.insper.edu.sistema_bancario.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public Collection<Cliente> listar() {
        return clienteService.listarClientes();
    }

    @PutMapping("/{cpf}")
    public Cliente editar(@PathVariable String cpf, @RequestBody Cliente cliente) {
        return clienteService.editarCliente(cpf, cliente);
    }

    @GetMapping("/{cpf}")
    public Cliente buscar(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }
}
