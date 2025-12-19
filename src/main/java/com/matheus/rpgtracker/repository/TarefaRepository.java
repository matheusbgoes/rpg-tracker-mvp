package com.matheus.rpgtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.rpgtracker.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Só de fazer isso, o Spring já cria automaticamente métodos como:
    // .save() -> Para salvar
    // .findAll() -> Para listar tudo
    // .deleteById() -> Para remover
    // É pura mágica! 🧙‍♂️
}