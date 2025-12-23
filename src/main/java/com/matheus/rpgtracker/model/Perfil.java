package com.matheus.rpgtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Perfil {
    @Id
    private Long id = 1L; 

    private String nomeHeroi;
    private String tituloSistema;

    // --- ESTES SÃO OS CAMPOS QUE ESTÃO FALTANDO ---
    private int nivel;
    private int xpTotal;
    // ----------------------------------------------

    private int forca;
    private int agilidade;
    private int inteligencia;
    private int vitalidade;
    private int pontosDisponiveis;

    public Perfil() {
    }

    public Perfil(String nomeHeroi, String tituloSistema) {
        this.nomeHeroi = nomeHeroi;
        this.tituloSistema = tituloSistema;
        this.nivel = 1;
        this.xpTotal = 0;
        this.forca = 10;
        this.agilidade = 10;
        this.inteligencia = 10;
        this.vitalidade = 10;
        this.pontosDisponiveis = 0;
    }

    // --- GETTERS E SETTERS MANUAIS (Para não precisar do Lombok) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeHeroi() { return nomeHeroi; }
    public void setNomeHeroi(String nomeHeroi) { this.nomeHeroi = nomeHeroi; }

    public String getTituloSistema() { return tituloSistema; }
    public void setTituloSistema(String tituloSistema) { this.tituloSistema = tituloSistema; }

    // --- Métodos que estavam dando erro no Controller ---
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getXpTotal() { return xpTotal; }
    public void setXpTotal(int xpTotal) { this.xpTotal = xpTotal; }
    // ---------------------------------------------------

    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }

    public int getAgilidade() { return agilidade; }
    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }

    public int getInteligencia() { return inteligencia; }
    public void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }

    public int getVitalidade() { return vitalidade; }
    public void setVitalidade(int vitalidade) { this.vitalidade = vitalidade; }

    public int getPontosDisponiveis() { return pontosDisponiveis; }
    public void setPontosDisponiveis(int pontosDisponiveis) { this.pontosDisponiveis = pontosDisponiveis; }
}