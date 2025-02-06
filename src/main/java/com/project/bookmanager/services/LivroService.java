package com.project.bookmanager.services;

import java.util.List;

import com.project.bookmanager.dto.LivroDTO;
import com.project.bookmanager.dto.LivroRequestDTO;

public interface LivroService {

    void create(LivroRequestDTO entity);
    List<LivroDTO> findAll();
    List<LivroDTO> findAllRead();
    List<LivroDTO> findAllNotRead();
    List<LivroDTO> findAllEmprestados();
    List<LivroDTO> findAllNotEmprestados();
    LivroDTO findById(Long id);
    List<LivroDTO> findByTerm(String term);
    List<LivroDTO> findByGeneros(List<String> generos);
    LivroDTO update(LivroRequestDTO entity, Long id);
    void updateReadStatus(Long id);
    void delete(Long id);
}
