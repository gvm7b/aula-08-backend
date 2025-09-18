package com.aulas_backend.aula08;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {


    private List<String> mensagens = new ArrayList<>();

    @GetMapping
    public List<String> Listar() {
        return mensagens;
    }

    @PostMapping("/adicionar")
    public String adicionar(@RequestBody String mensagem){
        mensagens.add(mensagem);
        return "Mensagem adicionada!";
    }
    @PutMapping
    public String atualizar(@PathVariable int index, @RequestBody String mensagem) {
        if(index >= 0 && index < mensagens.size()) {
            mensagens.add(mensagem);
            return "Mensagem atualizada";
        }
        return "Indice invalido ou array vazio";
    }

    @DeleteMapping("/{index}")
    public String remover(@PathVariable int index) {
        if (index >= 0 && index < mensagens.size()) {
            mensagens.remove(index);
            return "Mensagem removida!";
        }
        return "Indice invalido ou array vazio";
    }
}
