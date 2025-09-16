package br.insper.edu.sistema_bancario.model;

import java.time.LocalDate;

public class Movimentacao {
    private final Float valor;
    private final String tipo;
    private final LocalDate data;

    public Movimentacao(Float valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
        this.data = LocalDate.now();
    }

    public Float getValor() { return valor; }
    public String getTipo() { return tipo; }
    public LocalDate getData() { return data; }
}
