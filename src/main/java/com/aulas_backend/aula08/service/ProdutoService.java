package com.aulas_backend.aula08.service;

import com.aulas_backend.aula08.dto.ProdutoCreateDTO;
import com.aulas_backend.aula08.dto.ProdutoDTO;
import com.aulas_backend.aula08.models.Produto;
import com.aulas_backend.aula08.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoDTO toDTO(Produto produto){
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    private Produto toEntity(ProdutoCreateDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        return produto;
    }

    public List<ProdutoDTO> listarProdutos(){
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO salvar(ProdutoCreateDTO dto) {
        Produto produto = toEntity(dto);
        return toDTO(produtoRepository.save(produto));

    }

    public ProdutoDTO listarUmProduto(UUID id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Produto não encontrado!"));
        return toDTO(produto);
    }

    public ProdutoDTO atualizarProduto(UUID id, ProdutoCreateDTO dto){
        Produto existente = produtoRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Produto não encontrado!"));
        existente.setNome(dto.getNome());
        existente.setPreco(dto.getPreco());
        return toDTO(produtoRepository.save(existente));
    }

    public ProdutoDTO deletarProduto(UUID id){
        Produto existente = produtoRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Produto não encontrado!"));
        produtoRepository.delete(existente);
        return toDTO(existente);
    }

    public List<Produto> listarPeloNome(String nome){
        return produtoRepository.findByNome(nome);
    }

    public Collection<Produto> listarPrecoDesconhecido(){
        return produtoRepository.findByUnknownPrice();
    }
}
