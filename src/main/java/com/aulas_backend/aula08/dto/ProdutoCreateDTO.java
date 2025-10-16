package com.aulas_backend.aula08.dto;

import jakarta.validation.constraints.NotBlank;

public class ProdutoCreateDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotBlank(message = "O preço do produto é obrigatório.")
    private float preco;


    public ProdutoCreateDTO() {
    }

    public ProdutoCreateDTO(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
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
