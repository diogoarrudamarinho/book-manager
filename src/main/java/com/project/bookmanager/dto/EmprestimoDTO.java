package com.project.bookmanager.dto;

import java.time.LocalDate;

import com.project.bookmanager.entities.Emprestimo;

public class EmprestimoDTO {
    
    private Long id;
    private String tituloLivro;
    private String nomePessoa;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDTO() {
    }

    public EmprestimoDTO(Emprestimo entity){
        this.id = entity.getId();
        this.tituloLivro = entity.getLivro().getTitulo();
        this.nomePessoa = entity.getNomePessoa();
        this.dataEmprestimo = entity.getDataEmprestimo();
        this.dataDevolucao = entity.getDataDevolucao() == null ? 
                                    null : entity.getDataDevolucao();
    }

    public Long getId() {
        return id;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

}
