package com.rpgtracker.repository;

import com.rpgtracker.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

// Isso nos dá métodos prontos como .save(), .findAll(), .delete()
public interface CursoRepository extends JpaRepository <Curso, Long> {
}