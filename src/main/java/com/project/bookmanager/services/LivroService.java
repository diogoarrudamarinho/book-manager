package com.project.bookmanager.services;

import java.util.List;

import com.project.bookmanager.dto.LivroDTO;
import com.project.bookmanager.entities.Livro;

public interface LivroService {

    void create(Livro entity);
    List<LivroDTO> findAll();
    List<LivroDTO> findAllRead();
    List<LivroDTO> findAllNotRead();
    List<LivroDTO> findAllEmprestados();
    List<LivroDTO> findAllNotEmprestados();
    LivroDTO findById(Long id);
    List<LivroDTO> findByTerm(String term);
    List<LivroDTO> findByGenero(String genero);
    LivroDTO update(Livro entity, Long id);
    void updateReadStatus(Long id);
    void delete(Long id);
}
