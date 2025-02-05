package com.project.bookmanager.dto;

public class EmprestimoRequestDTO {
    
    private Long livroId;
    private String nomePessoa;

    public EmprestimoRequestDTO() {
    }

    public EmprestimoRequestDTO(Long livroId, String nomePessoa) {
        this.livroId = livroId;
        this.nomePessoa = nomePessoa;
    }

    public Long getLivroId() {
        return livroId;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }
}
