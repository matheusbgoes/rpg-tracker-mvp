package com.rpgtracker.controller;

import com.rpgtracker.model.Curso;
import com.rpgtracker.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping("/") // Quando o usuário acessar a página inicial
    public String listarCursos(Model model) {
        // Busca todos os cursos e calcula o XP total
        var cursos = repository.findAll();
        int xpTotal = cursos.stream()
                            .filter(c -> "Concluído".equals(c.getStatus()))
                            .mapToInt(Curso::getXpRecompensa)
                            .sum();
        
        // Nível simples: A cada 100 XP sobe um nível
        int nivel = (xpTotal / 100) + 1; 

        model.addAttribute("cursos", cursos);
        model.addAttribute("xpTotal", xpTotal);
        model.addAttribute("nivel", nivel);
        
        return "index"; // Vai procurar um arquivo index.html
    }

    @PostMapping("/salvar") // Quando o usuário clica em "Salvar"
    public String salvarCurso(Curso curso) {
        repository.save(curso);
        return "redirect:/"; // Recarrega a página
    }
}