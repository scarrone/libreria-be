package com.example.prova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prova.entities.Prestito;

@Repository
public interface PrestitiRepository extends JpaRepository<Prestito, Long> {
    
}
