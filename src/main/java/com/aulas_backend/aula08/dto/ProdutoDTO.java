package com.aulas_backend.aula08.dto;

import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    private String nome;
    private float preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(UUID id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
