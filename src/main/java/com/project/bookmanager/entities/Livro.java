package com.project.bookmanager.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_livro")
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulo obrigatório")
    private String titulo;

    private String autor;
    private String genero;

    @NotBlank(message = "Campo obrigatório")
    private Boolean lido;

    @NotBlank(message = "Campo obrigatório")
    private Boolean emprestado;

    private String imageURL;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;

    public Livro(){}

    public Livro(String autor, Boolean emprestado, String genero, Long id, Boolean lido, String titulo, String imageURL) {
        this.autor = autor;
        this.emprestado = emprestado;
        this.genero = genero;
        this.id = id;
        this.lido = lido;
        this.titulo = titulo;
        this.imageURL = imageURL;
    }

    public Livro(Long id, String titulo, String autor, String genero, Boolean lido, Boolean emprestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.lido = lido;
        this.emprestado = emprestado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean isLido() {
        return lido;
    }

    public void setLido(Boolean lido) {
        this.lido = lido;
    }

    public Boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        return Objects.equals(this.id, other.id);
    } 
}
