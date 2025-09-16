package br.insper.edu.sistema_bancario.service;


import br.insper.edu.sistema_bancario.model.ContaCorrente;
import br.insper.edu.sistema_bancario.model.Movimentacao;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContaCorrenteService {
    private final Map<String, ContaCorrente> contas = new HashMap<>();

    public ContaCorrente cadastrarConta(ContaCorrente conta) {
        if (contas.containsKey(conta.getNumero())) {
            throw new RuntimeException("Já existe conta com esse número");
        }
        contas.put(conta.getNumero(), conta);
        return conta;
    }

    public Collection<ContaCorrente> listarContas() {
        return contas.values();
    }

    public Float saque(String numero, Float valor) {
        ContaCorrente conta = contas.get(numero);
        if (conta == null) throw new RuntimeException("Conta não encontrada");
        if (!conta.saque(valor)) throw new RuntimeException("Saldo insuficiente");
        return valor;
    }

    public void deposito(String numero, Float valor) {
        ContaCorrente conta = contas.get(numero);
        if (conta == null) throw new RuntimeException("Conta não encontrada");
        conta.deposito(valor);
    }

    public Collection<Movimentacao> listaMovimentacoes(String numero) {
        ContaCorrente conta = contas.get(numero);
        if (conta == null) throw new RuntimeException("Conta não encontrada");
        return conta.listaMovimentacoes();
    }
}
