package com.project.bookmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookmanager.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query( "SELECT DISTINCT l FROM Livro l" +
            "WHERE LOWER(l.nome) LIKE %:term% " +
            "OR LOWER(l.autor) LIKE %:term% ")
    List<Livro> findAllByTerm(@Param("term")String term);
    
    List<Livro> findAllByGenero(String genero);

    @Query( "SELECT l FROM Livro l" + 
            "WHERE l.read = true")
    List<Livro> findAllRead();

    @Query( "SELECT l FROM Livro l" + 
            "WHERE l.read = false")
    List<Livro> findAllNotRead();

    @Query( "SELECT l FROM Livro l" + 
            "WHERE l.emprestado = true")
    List<Livro> findAllEmprestados();

    @Query( "SELECT l FROM Livro l" + 
            "WHERE l.emprestado = false")
    List<Livro> findAllNotEmprestados();
}
