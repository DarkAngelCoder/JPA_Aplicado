package com.examplo.juliana.controller;

import com.examplo.juliana.model.Instrutor;
import com.examplo.juliana.repository.InstrutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    private final InstrutorRepository instrutorRepository;

    public InstrutorController(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    @PostMapping
    public void criarInstrutor(@RequestBody Instrutor instrutor) {
        instrutorRepository.save(instrutor);
    }

    @GetMapping
    public List<Instrutor> listarTodos() {
        return instrutorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Instrutor buscarPorId(@PathVariable Long id) {
        return instrutorRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarInstrutor(@PathVariable Long id) {
        instrutorRepository.delete(id);
        return ResponseEntity.ok("Instrutor removido com sucesso");
    }

}
