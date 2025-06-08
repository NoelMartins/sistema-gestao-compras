package com.gestaocompras.repository;

import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long> {
    
    List<PedidoCompra> findByStatus(PedidoCompra.StatusPedido status);
    
    List<PedidoCompra> findByFornecedor(Fornecedor fornecedor);
    
    List<PedidoCompra> findByDataCriacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    @Query("SELECT p FROM PedidoCompra p WHERE p.status = :status AND p.fornecedor = :fornecedor")
    List<PedidoCompra> findByStatusAndFornecedor(@Param("status") PedidoCompra.StatusPedido status, @Param("fornecedor") Fornecedor fornecedor);
    
    @Query("SELECT p FROM PedidoCompra p WHERE p.dataCriacao BETWEEN :dataInicio AND :dataFim ORDER BY p.dataCriacao DESC")
    List<PedidoCompra> findPedidosPorPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}

