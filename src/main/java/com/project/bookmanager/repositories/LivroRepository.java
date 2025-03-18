package com.project.bookmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookmanager.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT DISTINCT l FROM Livro l " +
           "WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Livro> findAllByTerm(@Param("term")String term);
    
    @Query(  "SELECT DISTINCT l FROM Livro l " +
             "JOIN l.generos g WHERE LOWER(g.nome) IN :generos")
    List<Livro> findAllByGeneros(@Param("generos") List<String> generos);

    @Query( "SELECT l FROM Livro l " + 
            "WHERE l.lido = true")
    List<Livro> findAllRead();

    @Query( "SELECT l FROM Livro l " + 
            "WHERE l.lido = false")
    List<Livro> findAllNotRead();

    @Query( "SELECT l FROM Livro l " + 
            "WHERE l.emprestado = true")
    List<Livro> findAllEmprestados();

    @Query( "SELECT l FROM Livro l " + 
            "WHERE l.emprestado = false")
    List<Livro> findAllNotEmprestados();
}
