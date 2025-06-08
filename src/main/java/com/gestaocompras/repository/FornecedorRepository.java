package com.gestaocompras.repository;

import com.gestaocompras.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    Optional<Fornecedor> findByNome(String nome);
    
    List<Fornecedor> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT f FROM Fornecedor f WHERE f.contato IS NOT NULL AND f.contato != ''")
    List<Fornecedor> findFornecedoresComContato();
}

