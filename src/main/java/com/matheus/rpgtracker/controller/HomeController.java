package com.matheus.rpgtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("mensagem", "Sucesso! O Backend Spring Boot falou com o Frontend Thymeleaf.");
        return "index";
    }
}