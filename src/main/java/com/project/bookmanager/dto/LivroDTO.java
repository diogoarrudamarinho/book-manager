package com.project.bookmanager.dto;

import com.project.bookmanager.entities.Livro;

public class LivroDTO {
    
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private Boolean lido;
    private Boolean emprestado;

    public LivroDTO() {
    }

    public LivroDTO(Livro entity){
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.autor = entity.getAutor();
        this.genero = entity.getGenero();
        this.lido = entity.isLido();
        this.emprestado = entity.isEmprestado();
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

    public String getGenero() {
        return genero;
    }

    public Boolean getLido() {
        return lido;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

}
