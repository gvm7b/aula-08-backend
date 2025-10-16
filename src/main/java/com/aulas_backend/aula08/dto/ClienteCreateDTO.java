package com.aulas_backend.aula08.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteCreateDTO {
    @NotBlank(message = "O nome cliente é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail do cliente é obrigatório")
    @Email(message = "o e-mail deve ser válido")
    private String email;

    public ClienteCreateDTO() {
    }

    public ClienteCreateDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
