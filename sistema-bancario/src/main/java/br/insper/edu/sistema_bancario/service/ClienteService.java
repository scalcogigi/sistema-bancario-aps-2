package br.insper.edu.sistema_bancario.service;

import br.insper.edu.sistema_bancario.model.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {
    private final HashMap<String, Cliente> clientes = new HashMap<>();

    public Cliente cadastrarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getCpf())) {
            throw new RuntimeException("Já existe cliente com esse CPF");
        }
        clientes.put(cliente.getCpf(), cliente);
        return cliente;
    }

    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }

    public Cliente editarCliente(String cpf, Cliente clienteAtualizado) {
        Cliente existente = clientes.get(cpf);
        if (existente == null) throw new RuntimeException("Cliente não encontrado");

        existente.setNome(clienteAtualizado.getNome());
        existente.setDataNascimento(clienteAtualizado.getDataNascimento());
        existente.setSalario(clienteAtualizado.getSalario());
        return existente;
    }

    public Cliente buscarPorCpf(String cpf) {
        Cliente c = clientes.get(cpf);
        if (c == null) throw new RuntimeException("Cliente não encontrado");
        return c;
    }


    @PostMapping("/{numero}/saque")
    public Map<String, Object> saque(@PathVariable String numero,
                                     @RequestBody Map<String, Object> payload) {
        Float valor = Float.valueOf(payload.get("valor").toString());
        ContaCorrenteService contaService = null;
        Float saldoAtual = contaService.saque(numero, valor);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Saque realizado com sucesso");
        resposta.put("valorSacado", valor);
        resposta.put("saldoAtual", saldoAtual);
        return resposta;
    }
}

