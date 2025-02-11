package com.project.bookmanager.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookmanager.dto.LivroDTO;
import com.project.bookmanager.dto.LivroRequestDTO;
import com.project.bookmanager.entities.Genero;
import com.project.bookmanager.entities.Livro;
import com.project.bookmanager.repositories.GeneroRepository;
import com.project.bookmanager.repositories.LivroRepository;
import com.project.bookmanager.services.LivroService;

@Service
public class LivroServiceImp implements LivroService{
    
    @Autowired
    private LivroRepository repository;

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public void create(LivroRequestDTO entity) {

        List<String> generosLower = entity.getGeneros()
                                          .stream()
                                          .map(String::toLowerCase)
                                          .collect(Collectors.toList());

        List<Genero> generos = generoRepository.findAllByNomes(generosLower);
        
        if (generos.size() != entity.getGeneros().size())
            throw new IllegalArgumentException("Genero not found");

        Livro livro = new Livro();
        livro.setTitulo(entity.getTitulo());
        livro.setAutor(entity.getAutor());
        livro.setGeneros(generos);
        livro.setLido(entity.isLido());
        livro.setImageURL(entity.getImageURL());

        repository.save(livro);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<LivroDTO> findByGeneros(List<String> generos) {
        if (generos == null || generos.isEmpty()) 
            throw new IllegalArgumentException("List is null or empty");
        
        List<Livro> livros = repository.findAllByGeneros(generos);

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
    @Transactional
    public LivroDTO update(LivroRequestDTO entity, Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        List<String> generosLower = entity.getGeneros()
                                          .stream()
                                          .map(String::toLowerCase)
                                          .collect(Collectors.toList());

        List<Genero> generos = generoRepository.findAllByNomes(generosLower);
        
        if (generos.size() != entity.getGeneros().size())
            throw new IllegalArgumentException("Genero not found");

        Livro livro = repository.findById(id)
                                .orElseThrow(() -> 
                                new ObjectNotFoundException(
                                "Object not Found", id));

        livro.setTitulo(entity.getTitulo());
        livro.setAutor(entity.getAutor());
        livro.setGeneros(generos);
        livro.setLido(entity.isLido());
        livro.setImageURL(entity.getImageURL());

        repository.save(livro);

        return new LivroDTO(livro);
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        if(!repository.existsById(id))
            throw new ObjectNotFoundException("Object not found", id);

        repository.deleteById(id);
    }     
}
