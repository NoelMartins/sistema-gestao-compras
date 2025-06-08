package com.gestaocompras.controller;

import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;
    
    @GetMapping("/pedidos-por-periodo")
    public ResponseEntity<List<PedidoCompra>> gerarRelatorioPedidosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<PedidoCompra> pedidos = relatorioService.gerarRelatorioPedidosPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/produtos-mais-comprados")
    public ResponseEntity<List<Object[]>> gerarRelatorioProdutosMaisComprados() {
        List<Object[]> produtos = relatorioService.gerarRelatorioProdutosMaisComprados();
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/desempenho-fornecedores")
    public ResponseEntity<Map<String, Object>> gerarRelatorioDesempenhoFornecedores() {
        Map<String, Object> relatorio = relatorioService.gerarRelatorioDesempenhoFornecedores();
        return ResponseEntity.ok(relatorio);
    }
    
    @GetMapping("/estatisticas-gerais")
    public ResponseEntity<Map<String, Object>> gerarEstatisticasGerais() {
        Map<String, Object> estatisticas = relatorioService.gerarEstatisticasGerais();
        return ResponseEntity.ok(estatisticas);
    }
}

