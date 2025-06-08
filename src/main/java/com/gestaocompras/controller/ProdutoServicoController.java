package com.gestaocompras.controller;

import com.gestaocompras.model.ProdutoServico;
import com.gestaocompras.service.ProdutoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos-servicos")
@CrossOrigin(origins = "*")
public class ProdutoServicoController {
    
    @Autowired
    private ProdutoServicoService produtoServicoService;
    
    @GetMapping
    public ResponseEntity<List<ProdutoServico>> listarTodos() {
        List<ProdutoServico> produtos = produtoServicoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoServico> buscarPorId(@PathVariable Long id) {
        Optional<ProdutoServico> produto = produtoServicoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoServico>> buscarPorNome(@RequestParam String nome) {
        List<ProdutoServico> produtos = produtoServicoService.buscarPorNomeContendo(nome);
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/unidade-medida/{unidade}")
    public ResponseEntity<List<ProdutoServico>> buscarPorUnidadeMedida(@PathVariable String unidade) {
        List<ProdutoServico> produtos = produtoServicoService.buscarPorUnidadeMedida(unidade);
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/ordenado-por-preco")
    public ResponseEntity<List<ProdutoServico>> listarOrdenadoPorPreco() {
        List<ProdutoServico> produtos = produtoServicoService.listarOrdenadoPorPreco();
        return ResponseEntity.ok(produtos);
    }
    
    @PostMapping
    public ResponseEntity<ProdutoServico> criar(@Valid @RequestBody ProdutoServico produto) {
        try {
            ProdutoServico novoProduto = produtoServicoService.salvar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoServico> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoServico produto) {
        try {
            ProdutoServico produtoAtualizado = produtoServicoService.atualizar(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            produtoServicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

