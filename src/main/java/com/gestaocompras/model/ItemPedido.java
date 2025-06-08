package com.gestaocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Pedido de compra é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_compra_id", nullable = false)
    private PedidoCompra pedidoCompra;
    
    @NotNull(message = "Produto/Serviço é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_servico_id", nullable = false)
    private ProdutoServico produtoServico;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    @Column(nullable = false)
    private Integer quantidade;
    
    @NotNull(message = "Preço negociado é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço negociado deve ser maior que zero")
    @Column(name = "preco_negociado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoNegociado;
    
    // Construtores
    public ItemPedido() {}
    
    public ItemPedido(PedidoCompra pedidoCompra, ProdutoServico produtoServico, Integer quantidade, BigDecimal precoNegociado) {
        this.pedidoCompra = pedidoCompra;
        this.produtoServico = produtoServico;
        this.quantidade = quantidade;
        this.precoNegociado = precoNegociado;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public PedidoCompra getPedidoCompra() {
        return pedidoCompra;
    }
    
    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }
    
    public ProdutoServico getProdutoServico() {
        return produtoServico;
    }
    
    public void setProdutoServico(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public BigDecimal getPrecoNegociado() {
        return precoNegociado;
    }
    
    public void setPrecoNegociado(BigDecimal precoNegociado) {
        this.precoNegociado = precoNegociado;
    }
    
    // Método utilitário para calcular o valor total do item
    public BigDecimal getValorTotal() {
        return precoNegociado.multiply(BigDecimal.valueOf(quantidade));
    }
}

