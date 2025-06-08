package com.gestaocompras.controller;

import com.gestaocompras.model.Cotacao;
import com.gestaocompras.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cotacoes")
@CrossOrigin(origins = "*")
public class CotacaoController {
    
    @Autowired
    private CotacaoService cotacaoService;
    
    @GetMapping
    public ResponseEntity<List<Cotacao>> listarTodas() {
        List<Cotacao> cotacoes = cotacaoService.listarTodas();
        return ResponseEntity.ok(cotacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cotacao> buscarPorId(@PathVariable Long id) {
        Optional<Cotacao> cotacao = cotacaoService.buscarPorId(id);
        return cotacao.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Cotacao>> buscarPorProduto(@PathVariable Long produtoId) {
        List<Cotacao> cotacoes = cotacaoService.buscarPorProduto(produtoId);
        return ResponseEntity.ok(cotacoes);
    }
    
    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<Cotacao>> buscarPorFornecedor(@PathVariable Long fornecedorId) {
        List<Cotacao> cotacoes = cotacaoService.buscarPorFornecedor(fornecedorId);
        return ResponseEntity.ok(cotacoes);
    }
    
    @GetMapping("/produto/{produtoId}/comparar")
    public ResponseEntity<List<Cotacao>> compararCotacoesPorProduto(@PathVariable Long produtoId) {
        List<Cotacao> cotacoes = cotacaoService.compararCotacoesPorProduto(produtoId);
        return ResponseEntity.ok(cotacoes);
    }
    
    @GetMapping("/produto/{produtoId}/recentes")
    public ResponseEntity<List<Cotacao>> buscarCotacoesRecentesPorProduto(@PathVariable Long produtoId) {
        List<Cotacao> cotacoes = cotacaoService.buscarCotacoesRecentesPorProduto(produtoId);
        return ResponseEntity.ok(cotacoes);
    }
    
    @PostMapping
    public ResponseEntity<Cotacao> registrarCotacao(
            @RequestParam Long produtoId, 
            @RequestParam Long fornecedorId, 
            @Valid @RequestBody Cotacao cotacao) {
        try {
            Cotacao novaCotacao = cotacaoService.registrarCotacao(produtoId, fornecedorId, cotacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCotacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cotacao> atualizar(@PathVariable Long id, @Valid @RequestBody Cotacao cotacao) {
        try {
            Cotacao cotacaoAtualizada = cotacaoService.atualizar(id, cotacao);
            return ResponseEntity.ok(cotacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            cotacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

