package br.insper.edu.sistema_bancario.controller;

import br.insper.edu.sistema_bancario.model.ContaCorrente;
import br.insper.edu.sistema_bancario.model.Movimentacao;
import br.insper.edu.sistema_bancario.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteService contaService;

    @PostMapping
    public ContaCorrente cadastrar(@RequestBody ContaCorrente conta) {
        return contaService.cadastrarConta(conta);
    }

    @GetMapping
    public Collection<ContaCorrente> listar() {
        return contaService.listarContas();
    }

    @PostMapping("/{numero}/saque")
    public Map<String, Object> saque(@PathVariable String numero, @RequestBody Map<String, Object> payload) {
        Float valor = Float.valueOf(payload.get("valor").toString());
        contaService.saque(numero, valor);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Saque realizado com sucesso");
        resposta.put("valor", valor);
        return resposta;
    }

    @PostMapping("/{numero}/deposito")
    public void deposito(@PathVariable String numero, @RequestParam Float valor) {
        contaService.deposito(numero, valor);
    }

    @GetMapping("/{numero}/movimentacoes")
    public Collection<Movimentacao> movimentacoes(@PathVariable String numero) {
        return contaService.listaMovimentacoes(numero);
    }
}
