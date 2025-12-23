package com.matheus.rpgtracker.repository;

import com.matheus.rpgtracker.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    // Método mágico do Spring Data JPA
    // Tradução: "Busque Tudo Ordenado Por Status (Descendente) e ID (Descendente)"
    // Isso joga as concluídas para baixo e as novas para cima.
    List<Tarefa> findAllByOrderByStatusDescIdDesc();
}