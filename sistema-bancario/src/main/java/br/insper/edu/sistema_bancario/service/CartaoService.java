package br.insper.edu.sistema_bancario.service;

import br.insper.edu.sistema_bancario.model.Cartao;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartaoService {
    private final Map<String, Cartao> cartoes = new HashMap<>();

    public Cartao emitirCartao(Cartao cartao) {
        if (cartoes.containsKey(cartao.getNumeroCartao())) {
            throw new RuntimeException("Já existe cartão com esse número");
        }
        cartoes.put(cartao.getNumeroCartao(), cartao);
        return cartao;
    }

    public Collection<Cartao> listarTodos() {
        return cartoes.values();
    }

    public Collection<Cartao> listarPorConta(String numeroConta) {
        // aqui você pode associar cartão à conta, por enquanto retorna todos
        return cartoes.values();
    }

    public void cancelar(String numero) {
        Cartao c = cartoes.get(numero);
        if (c == null) throw new RuntimeException("Cartão não encontrado");
        c.cancelaCartao();
    }

    public boolean isAtivo(String numero) {
        Cartao c = cartoes.get(numero);
        if (c == null) throw new RuntimeException("Cartão não encontrado");
        return !"CANCELADO".equalsIgnoreCase(c.getStatus()) && !c.isExpired();
    }
}
