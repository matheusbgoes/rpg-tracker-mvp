package com.matheus.rpgtracker.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matheus.rpgtracker.model.Perfil;
import com.matheus.rpgtracker.model.Tarefa;
import com.matheus.rpgtracker.repository.PerfilRepository;
import com.matheus.rpgtracker.repository.TarefaRepository;

@Controller
public class HomeController {

    private final TarefaRepository tarefaRepository;
    private final PerfilRepository perfilRepository;

    // Injeção de dependência direta dos Repositórios
    public HomeController(TarefaRepository tarefaRepository, PerfilRepository perfilRepository) {
        this.tarefaRepository = tarefaRepository;
        this.perfilRepository = perfilRepository;
    }

    // Método auxiliar para garantir que o perfil sempre existe
    private Perfil carregarPerfil() {
        return perfilRepository.findById(1L)
                .orElseGet(() -> perfilRepository.save(new Perfil("Shadow Monarch", "System v1.0")));
    }

    @GetMapping("/")
    public String inicio(Model model) {
        Perfil perfil = carregarPerfil();

        // --- LÓGICA DE LEVEL UP ---
        // Calcula o nível baseado no XP Total acumulado no banco
        int novoNivel = (perfil.getXpTotal() / 100) + 1;

        if (novoNivel > perfil.getNivel()) {
            int diferenca = novoNivel - perfil.getNivel();
            perfil.setNivel(novoNivel);
            // Ganha 5 pontos de status por nível
            perfil.setPontosDisponiveis(perfil.getPontosDisponiveis() + (diferenca * 5));
            perfilRepository.save(perfil);
        }

        // Manda os dados para o HTML
        model.addAttribute("tarefas", tarefaRepository.findAllByOrderByStatusDescIdDesc());
        model.addAttribute("xpTotal", perfil.getXpTotal());
        model.addAttribute("nivel", perfil.getNivel());
        model.addAttribute("perfil", perfil);

        return "index";
    }

    @PostMapping("/salvar-perfil")
    public String salvarPerfil(@RequestParam String nomeHeroi, @RequestParam String tituloSistema) {
        Perfil perfil = carregarPerfil();
        // Atualiza apenas os nomes, mantendo o XP e Nível
        perfil.setNomeHeroi(nomeHeroi);
        perfil.setTituloSistema(tituloSistema);
        perfilRepository.save(perfil);
        return "redirect:/";
    }

    @PostMapping("/adicionar")
    public String adicionarTarefa(@RequestParam String descricao) {
        // Gera XP aleatório para a tarefa (Rank E a S)
        Random random = new Random();
        int xpReward = random.nextInt(41) + 10; // Entre 10 e 50 XP

        Tarefa novaTarefa = new Tarefa(descricao, xpReward);
        tarefaRepository.save(novaTarefa);
        return "redirect:/";
    }

    @GetMapping("/concluir/{id}")
    public String concluirTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        if (tarefa != null && !tarefa.getStatus().equals("Concluída")) {
            // 1. Marca como concluída
            tarefa.setStatus("Concluída");
            tarefaRepository.save(tarefa);

            // 2. Adiciona o XP ao Perfil do Jogador
            Perfil perfil = carregarPerfil();
            perfil.setXpTotal(perfil.getXpTotal() + tarefa.getXp());
            perfilRepository.save(perfil);
        }
        return "redirect:/";
    }

    @GetMapping("/excluir/{id}")
    public String excluirTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/distribuir/{atributo}")
    public String distribuirPonto(@PathVariable String atributo) {
        Perfil perfil = carregarPerfil();
        
        if (perfil.getPontosDisponiveis() > 0) {
            switch (atributo.toLowerCase()) {
                case "forca": perfil.setForca(perfil.getForca() + 1); break;
                case "agilidade": perfil.setAgilidade(perfil.getAgilidade() + 1); break;
                case "inteligencia": perfil.setInteligencia(perfil.getInteligencia() + 1); break;
                case "vitalidade": perfil.setVitalidade(perfil.getVitalidade() + 1); break;
            }
            // Gasta 1 ponto
            perfil.setPontosDisponiveis(perfil.getPontosDisponiveis() - 1);
            perfilRepository.save(perfil);
        }
        return "redirect:/";
    }
}