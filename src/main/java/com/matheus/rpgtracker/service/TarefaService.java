package com.matheus.rpgtracker.service;

import com.matheus.rpgtracker.model.Tarefa;
import com.matheus.rpgtracker.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public void salvar(Tarefa tarefa) {
        if (tarefa.getStatus() == null || tarefa.getStatus().isEmpty()) {
            tarefa.setStatus("Pendente");
        }
        tarefaRepository.save(tarefa);
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    public void concluir(Long id) {
        tarefaRepository.findById(id).ifPresent(tarefa -> {
            tarefa.setStatus("Concluída");
            tarefaRepository.save(tarefa);
        });
    }

    // --- MÉTODOS NOVOS DE GAMIFICAÇÃO (Faltavam estes!) ---

    public int calcularXpTotal() {
        // Soma o XP de todas as tarefas concluídas
        return tarefaRepository.findAll().stream()
                .filter(t -> "Concluída".equals(t.getStatus()))
                .mapToInt(Tarefa::getXp) // Pega o XP
                .sum();
    }

    public int calcularNivel(int xpTotal) {
        // Cálculo simples: Nível = (XP / 100) + 1
        return (xpTotal / 100) + 1;
    }
}