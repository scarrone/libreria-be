package com.example.prova.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prova.dto.LibroDTO;
import com.example.prova.services.LibroService;

import jakarta.validation.constraints.Size;


@RestController
@RequestMapping("/api/libro")
@Validated
@CrossOrigin("http://localhost:4200/")
public class LibriController {
    
    @Autowired
    private LibroService service;

    @GetMapping("")
    public ResponseEntity<List<LibroDTO>> findLibri() {
        return ResponseEntity.ok(service.getLibri());
    }

    @PostMapping("")
    public ResponseEntity<LibroDTO> addLibro(@RequestBody LibroDTO l) {
        return ResponseEntity.ok(service.addLibro(l));
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<LibroDTO> updateLibro(@PathVariable("isbn") String isbn, @RequestBody LibroDTO l) {
        return ResponseEntity.ok(service.updateLibro(l, isbn));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteLIbro(@PathVariable("isbn") @Size(min = 13, max = 13, message = "L'ISBN deve essere composto da 13 caratteri") String isbn){
        return ResponseEntity.ok(service.deleteLibroByIsbn(isbn));
    }
    
}
    