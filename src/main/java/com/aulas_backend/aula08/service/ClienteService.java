package com.aulas_backend.aula08.service;

import com.aulas_backend.aula08.dto.ClienteCreateDTO;
import com.aulas_backend.aula08.dto.ClienteDTO;
import com.aulas_backend.aula08.models.Cliente;
import com.aulas_backend.aula08.repository.ClienteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    private Cliente toEntity(ClienteCreateDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(cliente.getNome());
        cliente.setEmail(cliente.getEmail());
        return  cliente;
    }

    public List<ClienteDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO cadastrarCliente(ClienteCreateDTO dto){
        if(clienteRepository.existsByEmail(dto.getEmail())){
            throw new EntityExistsException("Já existe um cliente cadastrado com o e-mail informado.");
        }
        Cliente cliente = toEntity(dto);
        return toDTO(clienteRepository.save(cliente));
    }

    public ClienteDTO listarUmCliente(UUID id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado."));
        return toDTO(cliente);
    }

    public ClienteDTO atualizarCliente(UUID id, ClienteCreateDTO dto){
        Cliente clienteAtualizado = clienteRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado."));
        clienteAtualizado.setNome(dto.getNome());
        clienteAtualizado.setEmail(dto.getEmail());
        return toDTO(clienteRepository.save(clienteAtualizado));
    }

    public ClienteDTO deletarCliente(UUID id){
        Cliente clienteDeletado = clienteRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado."));
        clienteRepository.delete(clienteDeletado);
        return toDTO(clienteDeletado);
    }

    public List<Cliente> listarPeloNome(String nome){
        return clienteRepository.findByNome(nome);
    }


}
