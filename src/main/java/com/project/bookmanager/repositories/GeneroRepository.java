package com.project.bookmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookmanager.entities.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    List<Genero> findByNomeInIgnoreCase(List<String> generos);
    
}
