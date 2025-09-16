package br.insper.edu.sistema_bancario.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaCorrente {
    private String agencia;
    private String numero;
    private Float saldo;
    private final Float limite;

    private final List<Movimentacao> movimentacoes = new ArrayList<>();
    private final List<Cartao> cartoes = new ArrayList<>();

    public ContaCorrente() {
        this.limite = 0f;
    }

    public ContaCorrente(String agencia, String numero, Float saldoInicial, Float limite) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldoInicial == null ? 0f : saldoInicial;
        this.limite = limite == null ? 0f : limite;
    }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Float getSaldo() { return saldo; }
    public Float getLimite() { return limite; }

    public boolean saque(Float valor) {
        if (valor == null || valor <= 0f) return false;
        float novoSaldo = this.saldo - valor;
        if (novoSaldo < -this.limite) return false;
        this.saldo = novoSaldo;
        this.movimentacoes.add(new Movimentacao(valor, "SAQUE"));
        return true;
    }

    public boolean deposito(Float valor) {
        if (valor == null || valor <= 0f) return false;
        this.saldo += valor;
        this.movimentacoes.add(new Movimentacao(valor, "DEPOSITO"));
        return true;
    }

    public List<Movimentacao> listaMovimentacoes() {
        return Collections.unmodifiableList(movimentacoes);
    }

    public List<Cartao> listaCartoes() {
        return Collections.unmodifiableList(cartoes);
    }

    public void adicionarCartao(Cartao c) {
        if (c != null) cartoes.add(c);
    }

    public Cartao buscarCartaoPorNumero(String numeroCartao) {
        for (Cartao c : cartoes) {
            if (c.getNumeroCartao().equals(numeroCartao)) return c;
        }
        return null;
    }
}