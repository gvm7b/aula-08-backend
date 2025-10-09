package com.aulas_backend.aula08.service;

import com.aulas_backend.aula08.models.Cliente;
import com.aulas_backend.aula08.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente listarUmCliente(UUID id){
        return clienteRepository.findById(id).get();
    }

    public Cliente atualizarCliente(UUID id, Cliente cliente){
        Cliente clienteAtualizado = clienteRepository.findById(id).get();
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setEmail(cliente.getEmail());
        return clienteRepository.save(clienteAtualizado);
    }

    public Cliente deletarCliente(UUID id){
        Cliente clienteDeletado = clienteRepository.findById(id).get();
        clienteRepository.delete(clienteDeletado);
        return clienteDeletado;
    }

    public List<Cliente> listarPeloNome(String nome){
        return clienteRepository.findByNome(nome);
    }


}
