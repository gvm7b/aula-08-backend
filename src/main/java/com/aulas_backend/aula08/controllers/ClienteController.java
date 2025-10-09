package com.aulas_backend.aula08.controllers;

import com.aulas_backend.aula08.models.Cliente;
import com.aulas_backend.aula08.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarUm(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.listarUmCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID id, @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.atualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable UUID id){
        return ResponseEntity.ok(clienteService.deletarCliente(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> buscarPeloNome(@RequestParam String nome){
        return ResponseEntity.ok(clienteService.listarPeloNome(nome));
    }
}
