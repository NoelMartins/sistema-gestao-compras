package com.gestaocompras.service;

import com.gestaocompras.model.Cotacao;
import com.gestaocompras.model.Fornecedor;
import com.gestaocompras.model.ProdutoServico;
import com.gestaocompras.repository.CotacaoRepository;
import com.gestaocompras.repository.FornecedorRepository;
import com.gestaocompras.repository.ProdutoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {
    
    @Autowired
    private CotacaoRepository cotacaoRepository;
    
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;
    
    public List<Cotacao> listarTodas() {
        return cotacaoRepository.findAll();
    }
    
    public Optional<Cotacao> buscarPorId(Long id) {
        return cotacaoRepository.findById(id);
    }
    
    public List<Cotacao> buscarPorProduto(Long produtoId) {
        Optional<ProdutoServico> produto = produtoServicoRepository.findById(produtoId);
        if (produto.isPresent()) {
            return cotacaoRepository.findByProdutoServico(produto.get());
        }
        return List.of();
    }
    
    public List<Cotacao> buscarPorFornecedor(Long fornecedorId) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor.isPresent()) {
            return cotacaoRepository.findByFornecedor(fornecedor.get());
        }
        return List.of();
    }
    
    public List<Cotacao> compararCotacoesPorProduto(Long produtoId) {
        Optional<ProdutoServico> produto = produtoServicoRepository.findById(produtoId);
        if (produto.isPresent()) {
            return cotacaoRepository.findByProdutoServicoOrderByPreco(produto.get());
        }
        return List.of();
    }
    
    public Cotacao registrarCotacao(Long produtoId, Long fornecedorId, Cotacao cotacao) {
        ProdutoServico produto = produtoServicoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto/Serviço não encontrado com id: " + produtoId));
        
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + fornecedorId));
        
        cotacao.setProdutoServico(produto);
        cotacao.setFornecedor(fornecedor);
        
        return cotacaoRepository.save(cotacao);
    }
    
    public Cotacao atualizar(Long id, Cotacao cotacaoAtualizada) {
        return cotacaoRepository.findById(id)
                .map(cotacao -> {
                    cotacao.setPreco(cotacaoAtualizada.getPreco());
                    return cotacaoRepository.save(cotacao);
                })
                .orElseThrow(() -> new RuntimeException("Cotação não encontrada com id: " + id));
    }
    
    public void deletar(Long id) {
        if (!cotacaoRepository.existsById(id)) {
            throw new RuntimeException("Cotação não encontrada com id: " + id);
        }
        cotacaoRepository.deleteById(id);
    }
    
    public List<Cotacao> buscarCotacoesRecentesPorProduto(Long produtoId) {
        Optional<ProdutoServico> produto = produtoServicoRepository.findById(produtoId);
        if (produto.isPresent()) {
            return cotacaoRepository.findCotacoesRecentesPorProduto(produto.get());
        }
        return List.of();
    }
}

