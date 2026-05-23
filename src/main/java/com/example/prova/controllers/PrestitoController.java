package com.example.prova.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/prestito")
public class PrestitoController {
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PatchMapping("path")
    public String patchMethodName(@RequestBody String entity) {
        //TODO: process PATCH request
        return entity;
    }

    @DeleteMapping("path")
    public String deleteMethodName(@RequestParam String param) {
        //TODO: process DELETE request
        return param;
    }
    
}
