package com.gestaocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotacoes")
public class Cotacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Produto/Serviço é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_servico_id", nullable = false)
    private ProdutoServico produtoServico;
    
    @NotNull(message = "Fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(name = "data_cotacao")
    private LocalDateTime dataCotacao;
    
    // Construtores
    public Cotacao() {
        this.dataCotacao = LocalDateTime.now();
    }
    
    public Cotacao(ProdutoServico produtoServico, Fornecedor fornecedor, BigDecimal preco) {
        this();
        this.produtoServico = produtoServico;
        this.fornecedor = fornecedor;
        this.preco = preco;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public ProdutoServico getProdutoServico() {
        return produtoServico;
    }
    
    public void setProdutoServico(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }
    
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public LocalDateTime getDataCotacao() {
        return dataCotacao;
    }
    
    public void setDataCotacao(LocalDateTime dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}

