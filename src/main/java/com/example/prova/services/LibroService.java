    package com.example.prova.services;

    import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.prova.dto.LibroDTO;
import com.example.prova.entities.Autore;
import com.example.prova.entities.Libro;
import com.example.prova.exception.ResourceNotFoundException;
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
            if(repository.findByIsbn(l.getIsbn()).isPresent()) {
                throw new DataIntegrityViolationException("ISBN già presente");
            }

            if(l.getAuthorId() == null || l.getGenre() == null || l.getPublicationYear() == null || l.getTitle() == null || l.getIsbn() == null) {
                throw new IllegalArgumentException("Valorizzare tutti i valori obbligatori");
            }



            return libriMapper.toDTO(repository.save(libriMapper.toEntity(l, null)));
        }

        public LibroDTO updateLibro(LibroDTO libroDTO, String isbn) {
            Libro libro = getLibroByIsbn(isbn);
            
            if(libroDTO.getTitle() != null) {
                libro.setTitle(libroDTO.getTitle());
            }        
            if(libroDTO.getAuthorId() != null) {
                if(libro.getAuthor() == null) {
                    libro.setAuthor(new Autore());
                }
                libro.getAuthor().setId(libroDTO.getAuthorId());
            }
            if(libroDTO.getGenre() != null) {
                libro.setGenre(libroDTO.getGenre());
            }
            if(libroDTO.getPublicationYear() != null) {
                libro.setPublicationYear(libroDTO.getPublicationYear());
            }
            return libriMapper.toDTO(repository.save(libro)); 
        }

        public String deleteLibroByIsbn(String isbn) {
            getLibroByIsbn(isbn);
            repository.deleteByIsbn(isbn);
            return "Libro con ISBN " + isbn + " eliminato con successo";
        }

        public Libro getLibroByIsbn(String isbn) {
            Libro libro = repository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Libro non trovato"));
            return libro;
        }
        
    }
