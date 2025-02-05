package com.project.bookmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmanager.dto.LivroDTO;
import com.project.bookmanager.dto.LivroRequestDTO;
import com.project.bookmanager.services.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid LivroRequestDTO livro) {
        livroService.create(livro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll() {
        return ResponseEntity.ok(livroService.findAll());
    }
    
    @GetMapping("/read")
    public ResponseEntity<List<LivroDTO>> findAllRead() {
        return ResponseEntity.ok(livroService.findAllRead());
    }

    @GetMapping("/not-read")
    public ResponseEntity<List<LivroDTO>> findAllNotRead() {
        return ResponseEntity.ok(livroService.findAllNotRead());
    }

    @GetMapping("/emprestados")
    public ResponseEntity<List<LivroDTO>> findAllEmprestados() {
        return ResponseEntity.ok(livroService.findAllEmprestados());
    }

    @GetMapping("/not-emprestados")
    public ResponseEntity<List<LivroDTO>> findAllNotEmprestados() {
        return ResponseEntity.ok(livroService.findAllNotEmprestados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findByTerm(@RequestParam(value ="t", required = true) String term) {
        return ResponseEntity.ok(livroService.findByTerm(term));
    }

    @GetMapping("/generos")
    public ResponseEntity<List<LivroDTO>> findByGeneros(@RequestParam(value = "g", required = true) List<String> generos) {
        return ResponseEntity.ok(livroService.findByGeneros(generos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@RequestBody @Valid LivroRequestDTO livro, @PathVariable Long id) {
        return ResponseEntity.ok(livroService.update(livro, id));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> updateReadStatus(@PathVariable Long id) {
        livroService.updateReadStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
