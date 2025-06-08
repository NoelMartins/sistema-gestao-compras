package com.gestaocompras.repository;

import com.gestaocompras.model.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Long> {
    
    Optional<ProdutoServico> findByNome(String nome);
    
    List<ProdutoServico> findByNomeContainingIgnoreCase(String nome);
    
    List<ProdutoServico> findByUnidadeMedida(String unidadeMedida);
    
    @Query("SELECT p FROM ProdutoServico p WHERE p.precoReferencia IS NOT NULL ORDER BY p.precoReferencia ASC")
    List<ProdutoServico> findAllOrderByPrecoReferencia();
}

