package com.gestaocompras.controller;

import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.model.ItemPedido;
import com.gestaocompras.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos-compra")
@CrossOrigin(origins = "*")
public class PedidoCompraController {
    
    @Autowired
    private PedidoCompraService pedidoCompraService;
    
    @GetMapping
    public ResponseEntity<List<PedidoCompra>> listarTodos() {
        List<PedidoCompra> pedidos = pedidoCompraService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompra> buscarPorId(@PathVariable Long id) {
        Optional<PedidoCompra> pedido = pedidoCompraService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoCompra>> buscarPorStatus(@PathVariable PedidoCompra.StatusPedido status) {
        List<PedidoCompra> pedidos = pedidoCompraService.buscarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<PedidoCompra>> buscarPorFornecedor(@PathVariable Long fornecedorId) {
        List<PedidoCompra> pedidos = pedidoCompraService.buscarPorFornecedor(fornecedorId);
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<List<PedidoCompra>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<PedidoCompra> pedidos = pedidoCompraService.buscarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemPedido>> buscarItensDoPedido(@PathVariable Long id) {
        List<ItemPedido> itens = pedidoCompraService.buscarItensDoPedido(id);
        return ResponseEntity.ok(itens);
    }
    
    @PostMapping
    public ResponseEntity<PedidoCompra> criarPedido(@RequestParam Long fornecedorId, @RequestBody List<ItemPedido> itens) {
        try {
            PedidoCompra novoPedido = pedidoCompraService.criarPedido(fornecedorId, itens);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoCompra> atualizarStatus(@PathVariable Long id, @RequestParam PedidoCompra.StatusPedido status) {
        try {
            PedidoCompra pedidoAtualizado = pedidoCompraService.atualizarStatus(id, status);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PedidoCompra> cancelarPedido(@PathVariable Long id) {
        try {
            PedidoCompra pedidoCancelado = pedidoCompraService.cancelarPedido(id);
            return ResponseEntity.ok(pedidoCancelado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pedidoCompraService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

