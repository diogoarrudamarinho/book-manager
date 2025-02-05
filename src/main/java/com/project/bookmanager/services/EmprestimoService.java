package com.project.bookmanager.services;

import java.util.List;

import com.project.bookmanager.dto.EmprestimoDTO;
import com.project.bookmanager.dto.EmprestimoRequestDTO;

public interface EmprestimoService {
    
    void create(EmprestimoRequestDTO entity);
    EmprestimoDTO findById(Long id);
    List<EmprestimoDTO> findAll();
    List<EmprestimoDTO> findAllByNomePessoa(String nomePessoa);
    List<EmprestimoDTO> findAllEmprestimosEmAberto();
    EmprestimoDTO update(Long id, EmprestimoRequestDTO entity);
    void devolver(Long id);
    void delete(Long id);
}
