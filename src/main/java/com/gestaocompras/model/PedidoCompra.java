package com.gestaocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos_compra")
public class PedidoCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private StatusPedido status = StatusPedido.PENDENTE;
    
    @OneToMany(mappedBy = "pedidoCompra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemPedido> itens;
    
    // Enum para Status do Pedido
    public enum StatusPedido {
        PENDENTE, APROVADO, REJEITADO, CONCLUIDO, CANCELADO
    }
    
    // Construtores
    public PedidoCompra() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    public PedidoCompra(Fornecedor fornecedor) {
        this();
        this.fornecedor = fornecedor;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public StatusPedido getStatus() {
        return status;
    }
    
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    
    public List<ItemPedido> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}

