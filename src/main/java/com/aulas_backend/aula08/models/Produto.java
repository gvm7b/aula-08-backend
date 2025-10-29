package com.aulas_backend.aula08.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private float preco;

    @ManyToMany(mappedBy = "produtos")
    private List<Cliente> clientes = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, float preco) {
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
