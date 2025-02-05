package com.project.bookmanager.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookmanager.dto.EmprestimoDTO;
import com.project.bookmanager.dto.EmprestimoRequestDTO;
import com.project.bookmanager.entities.Emprestimo;
import com.project.bookmanager.entities.Livro;
import com.project.bookmanager.repositories.EmprestimoRepository;
import com.project.bookmanager.repositories.LivroRepository;
import com.project.bookmanager.services.EmprestimoService;

@Service
public class EmprestimoImp implements EmprestimoService {
    
    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void create(EmprestimoRequestDTO entity) {
        Emprestimo emprestimo = new Emprestimo();

        Livro livro = livroRepository.findById(entity
                                           .getLivroId())
                                           .orElseThrow(() ->
                                           new ObjectNotFoundException("Object not found", entity.getLivroId()));
        
        if(livro.isEmprestado())
            throw new IllegalArgumentException("Livro já emprestado");

        livro.setEmprestado(true);
        livroRepository.save(livro);

        emprestimo.setLivro(livro);
                                          
        emprestimo.setNomePessoa(entity.getNomePessoa());

        repository.save(emprestimo);
    }

    @Override
    @Transactional(readOnly = true)
    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = repository.findById(id)
                                          .orElseThrow(() -> 
                                          new ObjectNotFoundException("Object not found", id));

        return new EmprestimoDTO(emprestimo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmprestimoDTO> findAll() {
        List<Emprestimo> emprestimos = repository.findAll();

        return emprestimos.stream()
                          .map(EmprestimoDTO::new)
                          .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmprestimoDTO> findAllByNomePessoa(String nomePessoa) {
        List<Emprestimo> emprestimos = repository.findAllByNomePessoa(nomePessoa);

        return emprestimos.stream()
                          .map(EmprestimoDTO::new)
                          .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmprestimoDTO> findAllEmprestimosEmAberto() {
        List<Emprestimo> emprestimos = repository.findAllEmprestimosEmAberto();

        return emprestimos.stream()
                          .map(EmprestimoDTO::new)
                          .collect(Collectors.toList());
    }

    @Override
    public EmprestimoDTO update(Long id, EmprestimoRequestDTO entity) {
        if(id == null)
            throw new IllegalArgumentException("Object ID is null");

        Emprestimo emprestimo = repository.findById(id)
                                          .orElseThrow(() -> 
                                          new ObjectNotFoundException("Object not found", id));

        emprestimo.setNomePessoa(entity.getNomePessoa());

        emprestimo.setLivro(livroRepository.findById(entity
                                           .getLivroId())
                                           .orElseThrow(() ->
                                           new ObjectNotFoundException("Object not found", entity.getLivroId())));

        repository.save(emprestimo);

        return new EmprestimoDTO(emprestimo);
    }

    @Override
    public void devolver(Long id) {
        Emprestimo emprestimo = repository.findById(id)
                                          .orElseThrow(() -> 
                                          new ObjectNotFoundException("Object not found", id));

        Livro livro = emprestimo.getLivro();   
        livro.setEmprestado(false);
        livroRepository.save(livro);                              

        emprestimo.setDataDevolucao(java.time.LocalDate.now());

        repository.save(emprestimo);
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
