package br.com.senai.teste.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.senai.teste.model.Livros;


public interface LivrosRepository 
    extends JpaRepository<Livros, Integer>{
    

}
