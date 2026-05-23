package com.example.prova.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prova.dto.LibroDTO;
import com.example.prova.entities.Libro;
import com.example.prova.mapper.LIbriMapperImpl;
import com.example.prova.mapper.LibriMapper;
import com.example.prova.repositories.LibriRepository;

@Service
public class LibroService {
    
    private final LibriRepository repository;

    private final LibriMapper libriMapper;

    LibroService(LibriRepository repository, LibriMapper libriMapper) {
        this.repository = repository;
        this.libriMapper = libriMapper;
    }

    public List<LibroDTO> getLibri(){
        return libriMapper.toDTOList(repository.findAll());
    }

    public List<LibroDTO> addLibri(List<LibroDTO> l){
        return libriMapper.toDTOList(repository.saveAll(libriMapper.toEntityList(l)));
    }

    public LibroDTO addLibro(LibroDTO l){
        return libriMapper.toDTO(repository.save(libriMapper.toEntity(l, null)));
    }

    public LibroDTO updateLibro(LibroDTO l, Long id) {
        Libro libro = repository.findById(id).orElseThrow(() -> new RuntimeException("Libro non trovato"));
        libro.setTitle(l.getTitle());
        libro.setAuthor(l.getAuthor());
        libro.setGenre(l.getGenre());
        libro.setPublicationYear(l.getPublicationYear());
        return libriMapper.toDTO(repository.save(libro)); 
    }

    public void deleteLibro(Long id) {
        repository.deleteById(id);
    }
    
}
