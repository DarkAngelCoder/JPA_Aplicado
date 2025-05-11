package com.examplo.juliana.repository;

import com.examplo.juliana.model.Instrutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstrutorRepository {

    private final JdbcTemplate jdbcTemplate;

    public InstrutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Instrutor> mapRowToInstrutor = (rs, rowNum) -> {
        return new Instrutor(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("email")
        );
    };

    public void save(Instrutor instrutor) {
        jdbcTemplate.update(
                "INSERT INTO instrutor (nome, email) VALUES (?, ?)",
                instrutor.getNome(), instrutor.getEmail()
        );
    }

    public Instrutor findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM instrutor WHERE id = ?",
                mapRowToInstrutor, id
        );
    }

    public List<Instrutor> findAll() {
        return jdbcTemplate.query("SELECT * FROM instrutor", mapRowToInstrutor);
    }

    public void update(Instrutor instrutor) {
        jdbcTemplate.update(
                "UPDATE instrutor SET nome = ?, email = ? WHERE id = ?",
                instrutor.getNome(), instrutor.getEmail(), instrutor.getId()
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM instrutor WHERE id = ?", id);
    }
}

