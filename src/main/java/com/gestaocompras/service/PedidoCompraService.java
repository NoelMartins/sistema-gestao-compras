package com.gestaocompras.service;

import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.model.Fornecedor;
import com.gestaocompras.model.ItemPedido;
import com.gestaocompras.repository.PedidoCompraRepository;
import com.gestaocompras.repository.FornecedorRepository;
import com.gestaocompras.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoCompraService {
    
    @Autowired
    private PedidoCompraRepository pedidoCompraRepository;
    
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public List<PedidoCompra> listarTodos() {
        return pedidoCompraRepository.findAll();
    }
    
    public Optional<PedidoCompra> buscarPorId(Long id) {
        return pedidoCompraRepository.findById(id);
    }
    
    public List<PedidoCompra> buscarPorStatus(PedidoCompra.StatusPedido status) {
        return pedidoCompraRepository.findByStatus(status);
    }
    
    public List<PedidoCompra> buscarPorFornecedor(Long fornecedorId) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor.isPresent()) {
            return pedidoCompraRepository.findByFornecedor(fornecedor.get());
        }
        return List.of();
    }
    
    public List<PedidoCompra> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return pedidoCompraRepository.findPedidosPorPeriodo(dataInicio, dataFim);
    }
    
    @Transactional
    public PedidoCompra criarPedido(Long fornecedorId, List<ItemPedido> itens) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + fornecedorId));
        
        PedidoCompra pedido = new PedidoCompra(fornecedor);
        pedido = pedidoCompraRepository.save(pedido);
        
        // Associar itens ao pedido
        for (ItemPedido item : itens) {
            item.setPedidoCompra(pedido);
            itemPedidoRepository.save(item);
        }
        
        return pedido;
    }
    
    public PedidoCompra atualizarStatus(Long id, PedidoCompra.StatusPedido novoStatus) {
        return pedidoCompraRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(novoStatus);
                    return pedidoCompraRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));
    }
    
    public PedidoCompra cancelarPedido(Long id) {
        return atualizarStatus(id, PedidoCompra.StatusPedido.CANCELADO);
    }
    
    public void deletar(Long id) {
        if (!pedidoCompraRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }
        pedidoCompraRepository.deleteById(id);
    }
    
    public List<ItemPedido> buscarItensDoPedido(Long pedidoId) {
        return itemPedidoRepository.findByPedidoCompraId(pedidoId);
    }
}

