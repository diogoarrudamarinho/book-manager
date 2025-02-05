package com.project.bookmanager.dto;

import com.project.bookmanager.entities.Genero;

public class GeneroDTO {

    private Long id;
    private String nome;

    public GeneroDTO() {
    }
    
    public GeneroDTO(Genero genero) {
        this.id = genero.getId();
        this.nome = genero.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
