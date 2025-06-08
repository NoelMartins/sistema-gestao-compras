package com.gestaocompras.repository;

import com.gestaocompras.model.ItemPedido;
import com.gestaocompras.model.PedidoCompra;
import com.gestaocompras.model.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
    List<ItemPedido> findByPedidoCompra(PedidoCompra pedidoCompra);
    
    List<ItemPedido> findByProdutoServico(ProdutoServico produtoServico);
    
    @Query("SELECT i FROM ItemPedido i WHERE i.pedidoCompra.id = :pedidoId")
    List<ItemPedido> findByPedidoCompraId(@Param("pedidoId") Long pedidoId);
    
    @Query("SELECT i.produtoServico, SUM(i.quantidade) as totalQuantidade FROM ItemPedido i GROUP BY i.produtoServico ORDER BY totalQuantidade DESC")
    List<Object[]> findProdutosMaisComprados();
}

