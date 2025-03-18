package com.project.bookmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bookmanager.entities.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query( "SELECT g FROM Genero g "+ 
            "WHERE LOWER(g.nome) IN :generos")
    List<Genero> findAllByNomes(@Param("generos") List<String> generos);
    
}
