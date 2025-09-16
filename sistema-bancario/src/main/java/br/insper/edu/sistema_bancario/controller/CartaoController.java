package br.insper.edu.sistema_bancario.controller;

import br.insper.edu.sistema_bancario.model.Cartao;
import br.insper.edu.sistema_bancario.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public Cartao emitir(@RequestBody Cartao cartao) {
        return cartaoService.emitirCartao(cartao);
    }

    @GetMapping
    public Collection<Cartao> listarTodos() {
        return cartaoService.listarTodos();
    }

    @GetMapping("/conta/{numeroConta}")
    public Collection<Cartao> listarPorConta(@PathVariable String numeroConta) {
        return cartaoService.listarPorConta(numeroConta);
    }

    @DeleteMapping("/{numero}")
    public void cancelar(@PathVariable String numero) {
        cartaoService.cancelar(numero);
    }

    @GetMapping("/{numero}/ativo")
    public boolean ativo(@PathVariable String numero) {
        return cartaoService.isAtivo(numero);
    }
}
