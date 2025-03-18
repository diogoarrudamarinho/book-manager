package com.project.bookmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookmanager.entities.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
    @Query( "SELECT DISTINCT e FROM Emprestimo e " +
            "WHERE LOWER(e.nomePessoa) LIKE %:nome% ")
    List<Emprestimo> findAllByNomePessoa(@Param("nome")String nome);

    @Query( "SELECT e FROM Emprestimo e " + 
            "WHERE e.dataDevolucao IS NULL")
    List<Emprestimo> findAllEmprestimosEmAberto();
    
}
