package com.project.bookmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmprestimoRequestDTO {
   
    @NotNull(message = "Livro Id obrigatório")
    private Long livroId;

    @NotBlank(message = "Nome da pessoa obrigatório")
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
