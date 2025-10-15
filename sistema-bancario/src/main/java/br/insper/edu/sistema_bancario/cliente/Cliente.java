package br.insper.edu.sistema_bancario.cliente;

import br.insper.edu.sistema_bancario.contaCorrente.ContaCorrente;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Float salario;

    private ContaCorrente conta;

    public Cliente() {} // construtor vazio

    public Cliente(String cpf, String nome, LocalDate dataNascimento, Float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public Float getSalario() { return salario; }
    public void setSalario(Float salario) { this.salario = salario; }

    public ContaCorrente getConta() { return conta; }
    public void setConta(ContaCorrente conta) { this.conta = conta; }
}
