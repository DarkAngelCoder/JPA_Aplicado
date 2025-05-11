package com.examplo.juliana.repository;

import com.examplo.juliana.model.Curso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CursoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Curso> mapRowToCurso = (rs, rowNum) -> new Curso(
            rs.getLong("id"),
            rs.getString("titulo"),
            rs.getDouble("duracao_horas"),
            rs.getLong("instrutor_id")
    );

    public void save(Curso curso) {
        jdbcTemplate.update(
                "INSERT INTO curso (titulo, duracao_horas, instrutor_id) VALUES (?, ?, ?)",
                curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId()
        );
    }

    public Curso findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM curso WHERE id = ?",
                mapRowToCurso, id
        );
    }

    public List<Curso> findAll() {
        return jdbcTemplate.query("SELECT * FROM curso", mapRowToCurso);
    }

    public void update(Curso curso) {
        jdbcTemplate.update(
                "UPDATE curso SET titulo = ?, duracao_horas = ?, instrutor_id = ? WHERE id = ?",
                curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId(), curso.getId()
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM curso WHERE id = ?", id);
    }

    public List<Curso> buscarComFiltros(String titulo, Double duracaoMin, Double duracaoMax, Long instrutorId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM curso WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (titulo != null && !titulo.isEmpty()) {
            sql.append("AND LOWER(titulo) LIKE ? ");
            params.add("%" + titulo.toLowerCase() + "%");
        }

        if (duracaoMin != null) {
            sql.append("AND duracao_horas >= ? ");
            params.add(duracaoMin);
        }

        if (duracaoMax != null) {
            sql.append("AND duracao_horas <= ? ");
            params.add(duracaoMax);
        }

        if (instrutorId != null) {
            sql.append("AND instrutor_id = ? ");
            params.add(instrutorId);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), mapRowToCurso);
    }
}
