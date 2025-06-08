package com.gestaocompras.service;

import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.repository.PedidoCompraRepository;
import com.gestaocompras.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class RelatorioService {
    
    @Autowired
    private PedidoCompraRepository pedidoCompraRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public List<PedidoCompra> gerarRelatorioPedidosPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return pedidoCompraRepository.findPedidosPorPeriodo(dataInicio, dataFim);
    }
    
    public List<Object[]> gerarRelatorioProdutosMaisComprados() {
        return itemPedidoRepository.findProdutosMaisComprados();
    }
    
    public Map<String, Object> gerarRelatorioDesempenhoFornecedores() {
        Map<String, Object> relatorio = new HashMap<>();
        
        // Buscar todos os pedidos
        List<PedidoCompra> todosPedidos = pedidoCompraRepository.findAll();
        
        // Agrupar por fornecedor e calcular estatísticas
        Map<String, Integer> pedidosPorFornecedor = new HashMap<>();
        Map<String, Integer> pedidosConcluidosPorFornecedor = new HashMap<>();
        
        for (PedidoCompra pedido : todosPedidos) {
            String nomeFornecedor = pedido.getFornecedor().getNome();
            
            pedidosPorFornecedor.put(nomeFornecedor, 
                pedidosPorFornecedor.getOrDefault(nomeFornecedor, 0) + 1);
            
            if (pedido.getStatus() == PedidoCompra.StatusPedido.CONCLUIDO) {
                pedidosConcluidosPorFornecedor.put(nomeFornecedor,
                    pedidosConcluidosPorFornecedor.getOrDefault(nomeFornecedor, 0) + 1);
            }
        }
        
        relatorio.put("pedidosPorFornecedor", pedidosPorFornecedor);
        relatorio.put("pedidosConcluidosPorFornecedor", pedidosConcluidosPorFornecedor);
        
        return relatorio;
    }
    
    public Map<String, Object> gerarEstatisticasGerais() {
        Map<String, Object> estatisticas = new HashMap<>();
        
        List<PedidoCompra> todosPedidos = pedidoCompraRepository.findAll();
        
        long totalPedidos = todosPedidos.size();
        long pedidosPendentes = todosPedidos.stream()
            .filter(p -> p.getStatus() == PedidoCompra.StatusPedido.PENDENTE)
            .count();
        long pedidosAprovados = todosPedidos.stream()
            .filter(p -> p.getStatus() == PedidoCompra.StatusPedido.APROVADO)
            .count();
        long pedidosConcluidos = todosPedidos.stream()
            .filter(p -> p.getStatus() == PedidoCompra.StatusPedido.CONCLUIDO)
            .count();
        
        estatisticas.put("totalPedidos", totalPedidos);
        estatisticas.put("pedidosPendentes", pedidosPendentes);
        estatisticas.put("pedidosAprovados", pedidosAprovados);
        estatisticas.put("pedidosConcluidos", pedidosConcluidos);
        
        return estatisticas;
    }
}

