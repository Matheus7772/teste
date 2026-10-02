package br.com.senai.teste.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.senai.teste.model.Livros;
import br.com.senai.teste.service.LivrosService;

@RestController 
@RequestMapping ("/livros")
public class LivrosController {

    private final LivrosService livrosService;

    public LivrosController(LivrosService livrosService){
        this.livrosService = livrosService;
    }

@PostMapping 
public ResponseEntity<Livros> cadastrar(
    @RequestBody Livros livro){

        Livros livroCadastrado = livrosService.cadastrar(livro);

                return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Livros>> listar() {

        List<Livros> livros = livrosService.listar();

        return ResponseEntity.ok(livros);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Livros> buscarPorId(
        @PathVariable Integer id) {

        Optional<Livros> livros = livrosService.buscarPorId(id);

    if (livros.isPresent()) {
        return ResponseEntity.ok(livros.get());
    }

    return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> atualizar(
        @PathVariable Integer id,
        @RequestBody Livros novosDados) {

    Optional<Livros> livroAtualizado =
            livrosService.atualizar(id, novosDados);

    if (livroAtualizado.isPresent()) {
        return ResponseEntity.ok(livroAtualizado.get());
    }

    return ResponseEntity.notFound().build();
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> excluir(
        @PathVariable Integer id){
            boolean excluido = livrosService.excluir(id);
            if (excluido){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } 
    
}
