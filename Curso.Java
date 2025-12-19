package com.rpgtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Diz ao Java que isso é uma tabela no banco de dados
@Data // O Lombok cria automaticamente getters e setters(Economiza código)
public class Curso {

    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o ID será auto incrementado
    private Long id;

    private String nome;
    private String plataforma; // EX: Udemy, Coursera, etc.
    private String status; // EX: Em andamento, Concluído, Pendente
    private int xpRecompensa; // Quanto de XP vale este curso?
    
    // Contrutor vazio (obrigatório para JPA)
    public Curso() {}
}