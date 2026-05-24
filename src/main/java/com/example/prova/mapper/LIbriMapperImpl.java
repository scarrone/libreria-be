package com.example.prova.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.prova.dto.LibroDTO;
import com.example.prova.entities.Autore;
import com.example.prova.entities.Libro;
import com.example.prova.exception.ResourceNotFoundException;

import jakarta.annotation.Nullable;

@Component
public class LIbriMapperImpl implements LibriMapper {

    @Override
    public LibroDTO toDTO(Libro libro) {
        if (libro == null) {
            return null;
        }

        if(libro.getAuthor() == null) {
            throw new ResourceNotFoundException("Autore del libro non trovato");
        }

        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setTitle(libro.getTitle());
        libroDTO.setAuthorId(libro.getAuthor().getId());
        libroDTO.setGenre(libro.getGenre());
        libroDTO.setPublicationYear(libro.getPublicationYear());
        libroDTO.setIsbn(libro.getIsbn());
        return libroDTO;
    }

    @Override
    public Libro toEntity(LibroDTO libroDTO, @Nullable Long id) {
        if (libroDTO == null) {
            return null;
        }

        Libro libro = new Libro();
        libro.setAuthor(new Autore());
        libro.setId(id);
        libro.setTitle(libroDTO.getTitle());
        libro.getAuthor().setId(libroDTO.getAuthorId());
        libro.setGenre(libroDTO.getGenre());
        libro.setPublicationYear(libroDTO.getPublicationYear());
        libro.setIsbn(libroDTO.getIsbn());
        return libro;
    }

    @Override
    public List<LibroDTO> toDTOList(List<Libro> libri) {
        if (libri == null) {
            return null;
        }

        List<LibroDTO> dtoList = new ArrayList<>();
        for (Libro libro : libri) {
            dtoList.add(toDTO(libro));
        }
        return dtoList;
    }

    @Override
    public List<Libro> toEntityList(List<LibroDTO> libroDTOs) {
        if (libroDTOs == null) {
            return null;
        }

        List<Libro> entityList = new ArrayList<>();
        for (LibroDTO libroDTO : libroDTOs) {
            entityList.add(toEntity(libroDTO, null));
        }
        return entityList;
    }
    
}
