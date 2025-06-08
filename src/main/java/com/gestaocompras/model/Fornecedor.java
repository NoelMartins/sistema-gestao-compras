package com.gestaocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @Size(max = 255, message = "Contato deve ter no máximo 255 caracteres")
    private String contato;
    
    @Column(columnDefinition = "TEXT")
    private String endereco;
    
    @Column(name = "informacoes_bancarias", columnDefinition = "TEXT")
    private String informacoesBancarias;
    
    // Construtores
    public Fornecedor() {}
    
    public Fornecedor(String nome, String contato, String endereco, String informacoesBancarias) {
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.informacoesBancarias = informacoesBancarias;
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
    
    public String getContato() {
        return contato;
    }
    
    public void setContato(String contato) {
        this.contato = contato;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getInformacoesBancarias() {
        return informacoesBancarias;
    }
    
    public void setInformacoesBancarias(String informacoesBancarias) {
        this.informacoesBancarias = informacoesBancarias;
    }
}

