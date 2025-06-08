package com.gestaocompras.service;

import com.gestaocompras.model.ProdutoServico;
import com.gestaocompras.repository.ProdutoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServicoService {
    
    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;
    
    public List<ProdutoServico> listarTodos() {
        return produtoServicoRepository.findAll();
    }
    
    public Optional<ProdutoServico> buscarPorId(Long id) {
        return produtoServicoRepository.findById(id);
    }
    
    public Optional<ProdutoServico> buscarPorNome(String nome) {
        return produtoServicoRepository.findByNome(nome);
    }
    
    public List<ProdutoServico> buscarPorNomeContendo(String nome) {
        return produtoServicoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public List<ProdutoServico> buscarPorUnidadeMedida(String unidadeMedida) {
        return produtoServicoRepository.findByUnidadeMedida(unidadeMedida);
    }
    
    public ProdutoServico salvar(ProdutoServico produtoServico) {
        return produtoServicoRepository.save(produtoServico);
    }
    
    public ProdutoServico atualizar(Long id, ProdutoServico produtoAtualizado) {
        return produtoServicoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
                    produto.setPrecoReferencia(produtoAtualizado.getPrecoReferencia());
                    return produtoServicoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto/Serviço não encontrado com id: " + id));
    }
    
    public void deletar(Long id) {
        if (!produtoServicoRepository.existsById(id)) {
            throw new RuntimeException("Produto/Serviço não encontrado com id: " + id);
        }
        produtoServicoRepository.deleteById(id);
    }
    
    public List<ProdutoServico> listarOrdenadoPorPreco() {
        return produtoServicoRepository.findAllOrderByPrecoReferencia();
    }
}

