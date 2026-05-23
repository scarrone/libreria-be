package com.example.prova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prova.entities.Utente;

public interface UtentiRepository extends JpaRepository<Utente, Long> {
    
}
