package com.example.prova.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prova.entities.Libro;

@Repository
public interface LibriRepository extends JpaRepository<Libro, Long>{
    void deleteByIsbn(String isbn);

    Optional<Libro> findByIsbn(String isbn);
}
