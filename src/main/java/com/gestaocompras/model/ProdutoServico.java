package com.gestaocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos_servicos")
public class ProdutoServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Size(max = 50, message = "Unidade de medida deve ter no máximo 50 caracteres")
    @Column(name = "unidade_medida")
    private String unidadeMedida;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço de referência deve ser maior que zero")
    @Column(name = "preco_referencia", precision = 10, scale = 2)
    private BigDecimal precoReferencia;
    
    // Construtores
    public ProdutoServico() {}
    
    public ProdutoServico(String nome, String descricao, String unidadeMedida, BigDecimal precoReferencia) {
        this.nome = nome;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.precoReferencia = precoReferencia;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getUnidadeMedida() {
        return unidadeMedida;
    }
    
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    public BigDecimal getPrecoReferencia() {
        return precoReferencia;
    }
    
    public void setPrecoReferencia(BigDecimal precoReferencia) {
        this.precoReferencia = precoReferencia;
    }
}

