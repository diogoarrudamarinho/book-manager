package com.project.bookmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmanager.dto.EmprestimoDTO;
import com.project.bookmanager.dto.EmprestimoRequestDTO;
import com.project.bookmanager.services.EmprestimoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EmprestimoRequestDTO entity) {
        emprestimoService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmprestimoDTO>> findAll() {
        return ResponseEntity.ok(emprestimoService.findAll());
    }
    
    @GetMapping()
    public ResponseEntity<List<EmprestimoDTO>> findAllByNomePessoa(@RequestParam(value = "name", required = true) String nomePessoa) {
        return ResponseEntity.ok(emprestimoService.findAllByNomePessoa(nomePessoa));
    }

    @GetMapping("/abertos")
    public ResponseEntity<List<EmprestimoDTO>> findAllEmprestimosEmAberto() {
        return ResponseEntity.ok(emprestimoService.findAllEmprestimosEmAberto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoRequestDTO entity) {
        return ResponseEntity.ok(emprestimoService.update(id, entity));
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        emprestimoService.devolver(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
