package com.example.prova.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prova.entities.Libro;
import com.example.prova.services.LibroService;


@RestController
@RequestMapping("/api/libri")
@CrossOrigin("http://localhost:4200/")
public class LibriController {
    
    @Autowired
    private LibroService service;

    @GetMapping({"", "/"})
    public List<Libro> findLibri() {
        return service.getLibri();
    }

    @PostMapping({"", "/"})
    public void addLibri(@RequestBody List<Libro> l) {
        service.addLibri(l);
    }
    
}
