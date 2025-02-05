package com.project.bookmanager.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookmanager.dto.LivroDTO;
import com.project.bookmanager.entities.Livro;
import com.project.bookmanager.repositories.LivroRepository;
import com.project.bookmanager.services.LivroService;

@Service
public class LivroServiceImp implements LivroService{
    
    @Autowired
    private LivroRepository repository;

    @Override
    public void create(Livro entity) {
        if(entity.getId() != null && repository.existsById(entity.getId()))
            throw new IllegalArgumentException("ID already exists");

        repository.save(entity);
    }

    @Override
    public List<LivroDTO> findAll() {
        List<Livro> livros = repository.findAll();

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> findAllRead() {
        List<Livro> livros = repository.findAllRead();

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override  
    @Transactional(readOnly = true)
    public List<LivroDTO> findAllNotRead() {
        List<Livro> livros = repository.findAllNotRead();

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override  
    @Transactional(readOnly = true)
    public List<LivroDTO> findAllEmprestados() {
        List<Livro> livros = repository.findAllEmprestados();

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override  
    @Transactional(readOnly = true)
    public List<LivroDTO> findAllNotEmprestados() {
        List<Livro> livros = repository.findAllNotEmprestados();

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> findByTerm(String term) {

        if(term == null || term.isBlank())
            throw new IllegalArgumentException("Term is null");
            
        List<Livro> livros = repository.findAllByTerm(term.toLowerCase());

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override
    public List<LivroDTO> findByGenero(String genero) {
        if(genero == null || genero.isBlank())
            throw new IllegalArgumentException("Genero is null");

        List<Livro> livros = repository.findAllByGenero(genero.toLowerCase());

        return livros.stream()
                      .map(LivroDTO::new)
                      .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        Livro livro = repository.findById(id)
                              .orElseThrow(() -> 
                              new ObjectNotFoundException("Object not Found", id));
   
        return new LivroDTO(livro);
    }

    @Override
    public LivroDTO update(Livro entity, Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        Livro livro = repository.findById(id)
                                .orElseThrow(() -> 
                                new ObjectNotFoundException(
                                "Object not Found", id));

        livro.setTitulo(entity.getTitulo());
        livro.setAutor(entity.getAutor());
        livro.setGenero(entity.getGenero());
        livro.setLido(entity.isLido());
        livro.setImageURL(entity.getImageURL());

        repository.save(livro);

        return new LivroDTO(livro);
    }

    @Override
    public void updateReadStatus(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        Livro livro = repository.findById(id)
                                .orElseThrow(() -> 
                                new ObjectNotFoundException("Object not Found", id));

        livro.setLido(true);

        repository.save(livro);
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        if(!repository.existsById(id))
            throw new ObjectNotFoundException("Object not found", id);

        repository.deleteById(id);
    }     
}
