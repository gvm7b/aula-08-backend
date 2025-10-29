package com.aulas_backend.aula08.repository;

import com.aulas_backend.aula08.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByNome(String nome);

    boolean existsByEmail(String email);

    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}

