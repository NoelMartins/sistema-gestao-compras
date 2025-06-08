package com.gestaocompras.service;

import com.gestaocompras.model.Fornecedor;
import com.gestaocompras.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }
    
    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }
    
    public Optional<Fornecedor> buscarPorNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }
    
    public List<Fornecedor> buscarPorNomeContendo(String nome) {
        return fornecedorRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
    
    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        return fornecedorRepository.findById(id)
                .map(fornecedor -> {
                    fornecedor.setNome(fornecedorAtualizado.getNome());
                    fornecedor.setContato(fornecedorAtualizado.getContato());
                    fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
                    fornecedor.setInformacoesBancarias(fornecedorAtualizado.getInformacoesBancarias());
                    return fornecedorRepository.save(fornecedor);
                })
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + id));
    }
    
    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RuntimeException("Fornecedor não encontrado com id: " + id);
        }
        fornecedorRepository.deleteById(id);
    }
    
    public List<Fornecedor> listarFornecedoresComContato() {
        return fornecedorRepository.findFornecedoresComContato();
    }
}

