package com.example.prova.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prova.dto.PrestitoDTO;
import com.example.prova.entities.Prestito;
import com.example.prova.services.PrestitoService;



@RestController
@RequestMapping("api/prestito")
public class PrestitoController {

    @Autowired
    private PrestitoService prestitoService;

    @GetMapping("")
    public ResponseEntity<List<Prestito>> getPrestiti() {
        return ResponseEntity.ok(prestitoService.getPrestiti());
    }

    @PostMapping("")
    public ResponseEntity<Prestito> postMethodName(@RequestBody PrestitoDTO entity) {
        return ResponseEntity.ok(prestitoService.createPrestito(entity));
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<Prestito> patchMethodName(Date dataScadenza, Date dataRestituzione,@PathVariable("id") Long id) {
        return ResponseEntity.ok(prestitoService.updatePrestito(dataScadenza, dataRestituzione, id));
    }
}
