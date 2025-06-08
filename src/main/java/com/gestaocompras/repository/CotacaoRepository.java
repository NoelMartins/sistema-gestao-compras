package com.gestaocompras.repository;

import com.gestaocompras.model.Cotacao;
import com.gestaocompras.model.Fornecedor;
import com.gestaocompras.model.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    
    List<Cotacao> findByProdutoServico(ProdutoServico produtoServico);
    
    List<Cotacao> findByFornecedor(Fornecedor fornecedor);
    
    @Query("SELECT c FROM Cotacao c WHERE c.produtoServico = :produto ORDER BY c.preco ASC")
    List<Cotacao> findByProdutoServicoOrderByPreco(@Param("produto") ProdutoServico produto);
    
    @Query("SELECT c FROM Cotacao c WHERE c.produtoServico.id = :produtoId AND c.fornecedor.id = :fornecedorId")
    List<Cotacao> findByProdutoAndFornecedor(@Param("produtoId") Long produtoId, @Param("fornecedorId") Long fornecedorId);
    
    @Query("SELECT c FROM Cotacao c WHERE c.produtoServico = :produto ORDER BY c.dataCotacao DESC")
    List<Cotacao> findCotacoesRecentesPorProduto(@Param("produto") ProdutoServico produto);
}

