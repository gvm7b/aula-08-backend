package com.aulas_backend.aula08.controllers;

import com.aulas_backend.aula08.dto.ClienteCreateDTO;
import com.aulas_backend.aula08.dto.ClienteDTO;
import com.aulas_backend.aula08.models.Cliente;
import com.aulas_backend.aula08.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteCreateDTO dto){
        return ResponseEntity.ok(clienteService.cadastrarCliente(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarUm(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.listarUmCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@Valid @PathVariable UUID id, @RequestBody ClienteCreateDTO dto){
        return ResponseEntity.ok(clienteService.atualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> deletar(@PathVariable UUID id){
        return ResponseEntity.ok(clienteService.deletarCliente(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> buscarPeloNome(@RequestParam String nome){
        return ResponseEntity.ok(clienteService.listarPeloNome(nome));
    }

    @GetMapping("/paginado")
    public Page<Cliente> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String nomeFiltro
    ){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return clienteService.listarPaginado(nomeFiltro, pageable);
    }
}
