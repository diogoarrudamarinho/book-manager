package com.project.bookmanager.dto;

import java.util.List;

import com.project.bookmanager.entities.Livro;

public class LivroDTO {
    
    private Long id;
    private String titulo;
    private String autor;
    private Boolean lido;
    private Boolean emprestado;
    private String imageURL;
    private List<GeneroDTO> generos;

    public LivroDTO() {
    }
    
    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.lido = livro.isLido();
        this.emprestado = livro.isEmprestado();
        this.autor = livro.getAutor() == null ? 
                            null : livro.getAutor();
        this.imageURL = livro.getImageURL() == null ? 
                            null : livro.getImageURL();
        this.generos = livro.getGeneros()
                            .stream()
                            .map(GeneroDTO::new)
                            .toList();
    }

    public Long getId() {
        return id;
    }

    public String gettitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<GeneroDTO> getGeneros() {
        return generos;
    }

    public Boolean isLido() {
        return lido;
    }

    public Boolean isEmprestado() {
        return emprestado;
    }
}
