package com.example.prova.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prova.entities.Autore;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, Long> {
    
}
