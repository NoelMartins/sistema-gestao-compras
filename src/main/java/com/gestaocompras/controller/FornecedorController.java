package com.gestaocompras.controller;

import com.gestaocompras.model.Fornecedor;
import com.gestaocompras.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "*")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;
    
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
        return fornecedor.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Fornecedor>> buscarPorNome(@RequestParam String nome) {
        List<Fornecedor> fornecedores = fornecedorService.buscarPorNomeContendo(nome);
        return ResponseEntity.ok(fornecedores);
    }
    
    @GetMapping("/com-contato")
    public ResponseEntity<List<Fornecedor>> listarComContato() {
        List<Fornecedor> fornecedores = fornecedorService.listarFornecedoresComContato();
        return ResponseEntity.ok(fornecedores);
    }
    
    @PostMapping
    public ResponseEntity<Fornecedor> criar(@Valid @RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor novoFornecedor = fornecedorService.salvar(fornecedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @Valid @RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor fornecedorAtualizado = fornecedorService.atualizar(id, fornecedor);
            return ResponseEntity.ok(fornecedorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            fornecedorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

