package com.project.bookmanager.dto;

import java.util.List;

import com.project.bookmanager.entities.Genero;
import com.project.bookmanager.entities.Livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LivroRequestDTO {
    
    @NotBlank(message = "Título do livro é obrigatório")
    private String titulo;

    private String autor;

    @NotNull(message = "Campo 'lido' obrigatório")
    private Boolean lido; 

    @NotNull(message = "Campo 'emprestado' obrigatório")
    private Boolean emprestado;

    private String imageURL;

    @NotEmpty(message = "Selecione pelo menos um gênero")
    private List<@Positive(message = "Id Inválido") Long> generosIds; 

    public LivroRequestDTO() {
    }

    public LivroRequestDTO(Livro entity) {
        this.titulo = entity.getTitulo();
        this.autor = entity.getAutor();
        this.lido = entity.isLido();
        this.emprestado = entity.isEmprestado();
        this.imageURL = entity.getImageURL();
        this.generosIds = entity.getGeneros()
                            .stream()
                            .map(Genero::getId)
                            .toList();
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

    public List<Long> getGenerosIds() {
        return generosIds;
    }

    
}
