package com.example.prova.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prova.entities.Libro;
import com.example.prova.repositories.LibriRepository;

@Service
public class LibroService {
    
    @Autowired
    private LibriRepository repository;

    public List<Libro> getLibri(){
        return repository.findAll();
    }

    public void addLibri(List<Libro> l){
        repository.saveAll(l);
    }

}
