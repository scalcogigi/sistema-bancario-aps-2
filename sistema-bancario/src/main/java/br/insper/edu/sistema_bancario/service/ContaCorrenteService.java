package br.insper.edu.sistema_bancario.service;

import br.insper.edu.sistema_bancario.model.ContaCorrente;
import br.insper.edu.sistema_bancario.model.Movimentacao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (conta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
        }
        if (valor == null || valor <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor inválido");
        }
        if (!conta.saque(valor)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
        }
        return conta.getSaldo();
    }


    public Float deposito(String numero, Float valor) {
        ContaCorrente conta = contas.get(numero);
        if (conta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
        }
        if (valor == null || valor <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor inválido");
        }
        conta.deposito(valor);
        return conta.getSaldo();
    }

    public Collection<Movimentacao> listaMovimentacoes(String numero) {
        ContaCorrente conta = contas.get(numero);
        if (conta == null) throw new RuntimeException("Conta não encontrada");
        return conta.listaMovimentacoes();
    }
}
