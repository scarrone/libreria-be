package com.example.prova.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping("/{id}")
    public void updateLibro(@PathVariable("id") Long id, @RequestBody Libro l) {
        if(id != null){
            l.setId(id);
        }
        service.updateLibro(l);
    }

    @DeleteMapping("/{id}")
    public void deleteLIbro(@PathVariable("id") Long id){
        service.deleteLibro(id);
    }
    
}
