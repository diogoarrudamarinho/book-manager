package com.project.bookmanager.dto;

import com.project.bookmanager.entities.Livro;

public class LivroMinDTO {
    
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private String imageURL;
    
    public LivroMinDTO() {
    }

    public LivroMinDTO(Livro Entity){
        this.id = Entity.getId();
        this.titulo = Entity.getTitulo();
        this.genero = Entity.getGenero();
        this.imageURL = Entity.getImageURL();
        this.autor = Entity.getAutor();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAutor() {
        return autor;
    }
}
