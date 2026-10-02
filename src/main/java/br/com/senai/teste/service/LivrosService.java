package br.com.senai.teste.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.com.senai.teste.model.Livros;
import br.com.senai.teste.repository.LivrosRepository;

@Service 
public class LivrosService {
    private final LivrosRepository livrosRepository;

    public LivrosService(LivrosRepository livrosRepository){
        this.livrosRepository = livrosRepository;

    }

    public Livros cadastrar(Livros livro){
        return livrosRepository.save(livro);
    }

     public List<Livros> listar() {
        return livrosRepository.findAll();
    }
    
    public Optional<Livros>buscarPorId(Integer id){
        return livrosRepository.findById(id);
    }

    public Optional<Livros> atualizar(
        Integer id, Livros novosDados) {

    Optional<Livros> livroEncontrado = livrosRepository.findById(id);

    if (livroEncontrado.isEmpty()) {
        return Optional.empty();
    }

    Livros livros = livroEncontrado.get();

    livros.setTitulo(novosDados.getTitulo());
    livros.setAutor(novosDados.getAutor());
    livros.setAnoPublicacao(novosDados.getAnoPublicacao());

    return Optional.of(livrosRepository.save(livros));
}

public boolean excluir(Integer id) {
    if (!livrosRepository.existsById(id)){
        return false;
    }

    livrosRepository.deleteById(id);
    return true;
    }
}
