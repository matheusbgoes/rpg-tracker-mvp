package com.matheus.rpgtracker.repository;

import com.matheus.rpgtracker.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}