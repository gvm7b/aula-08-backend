package com.aulas_backend.aula08.controllers;


import com.aulas_backend.aula08.dto.ProdutoCreateDTO;
import com.aulas_backend.aula08.dto.ProdutoDTO;
import com.aulas_backend.aula08.models.Produto;
import com.aulas_backend.aula08.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar(){
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoCreateDTO dto) {
        return ResponseEntity.ok(produtoService.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> listarUm(@PathVariable UUID id){
        return ResponseEntity.ok(produtoService.listarUmProduto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @PathVariable UUID id, @RequestBody ProdutoCreateDTO dto){
        return ResponseEntity.ok(produtoService.atualizarProduto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDTO> deletar(@PathVariable UUID id){
        return ResponseEntity.ok(produtoService.deletarProduto(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Produto>> buscarPeloNome(@RequestParam String nome){
        return ResponseEntity.ok(produtoService.listarPeloNome(nome));
    }

    @GetMapping("/produto-desconhecido")
    public ResponseEntity<Collection<Produto>> buscarPrecoDesconhecido(){
        return ResponseEntity.ok(produtoService.listarPrecoDesconhecido());
    }
}
