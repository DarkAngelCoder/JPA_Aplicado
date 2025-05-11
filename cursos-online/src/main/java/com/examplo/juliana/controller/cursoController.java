package com.examplo.juliana.controller;

import com.examplo.juliana.model.Curso;
import com.examplo.juliana.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class cursoController {

    private final CursoRepository cursoRepository;

    public cursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Método para criar um novo curso
    @PostMapping
    public void criarCurso(@RequestBody Curso curso) {
        cursoRepository.save(curso);
    }

    // Método para listar todos os cursos
    @GetMapping
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    // Método para buscar curso por ID
    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable Long id) {
        return cursoRepository.findById(id);
    }

    // Método para buscar cursos com filtros
    @GetMapping("/filtro")
    public List<Curso> buscarComFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double duracaoMin,
            @RequestParam(required = false) Double duracaoMax,
            @RequestParam(required = false) Long instrutorId
    ) {
        return cursoRepository.buscarComFiltros(titulo, duracaoMin, duracaoMax, instrutorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCurso(@PathVariable Long id) {
        cursoRepository.delete(id);
        return ResponseEntity.ok("Curso cancelado com sucesso");
    }


}
