package com.aulas_backend.aula08.controllers;

import com.aulas_backend.aula08.dto.UsuarioCreateDTO;
import com.aulas_backend.aula08.dto.UsuarioDTO;
import com.aulas_backend.aula08.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioCreateDTO dto){
        return ResponseEntity.ok(usuarioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @PathVariable UUID id, @RequestBody UsuarioCreateDTO dto){
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<UsuarioDTO> deletar(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.deletar(id));
    }
}
