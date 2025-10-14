package com.aulas_backend.aula08.service;

import com.aulas_backend.aula08.dto.UsuarioCreateDTO;
import com.aulas_backend.aula08.dto.UsuarioDTO;
import com.aulas_backend.aula08.models.Usuario;
import com.aulas_backend.aula08.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    private Usuario toEntity(UsuarioCreateDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    public UsuarioDTO salvar(UsuarioCreateDTO dto){
        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new EntityExistsException("Já existe um usuário cadastrado com o e-mail informado.");
        }

        Usuario usuario = toEntity(dto);
        return toDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(UUID id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuário não encontrado"));
        return toDTO(usuario);
    }

    public UsuarioDTO atualizar(UUID id, UsuarioCreateDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado"));
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO deletar(UUID id){
        Usuario removido = usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado"));
        usuarioRepository.delete(removido);
        return toDTO(removido);
    }

}
