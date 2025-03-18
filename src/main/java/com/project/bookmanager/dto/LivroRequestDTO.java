package com.project.bookmanager.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LivroRequestDTO {
    
    @NotBlank(message = "Título do livro é obrigatório")
    private String titulo;

    private String autor;

    @NotNull(message = "Campo 'lido' obrigatório")
    private Boolean lido; 

    @NotNull(message = "Campo 'emprestado' obrigatório")
    private Boolean emprestado;

    private String imageURL;

    @NotEmpty(message = "Campo 'generos' obrigatório")
    private List<@NotBlank String> generos = new ArrayList<>();

    public LivroRequestDTO() {
    }

    public LivroRequestDTO(String titulo, String autor, Boolean lido, Boolean emprestado, String imageURL, List<String> generos) {
        this.titulo = titulo;
        this.autor = autor;
        this.lido = lido;
        this.emprestado = emprestado;
        this.imageURL = imageURL;
        this.generos = generos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Boolean isLido() {
        return lido;
    }

    public Boolean isEmprestado() {
        return emprestado;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<String> getGeneros() {
        return generos;
    }

    
}
