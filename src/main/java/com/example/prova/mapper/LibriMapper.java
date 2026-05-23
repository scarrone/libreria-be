package com.example.prova.mapper;

import java.util.List;

import com.example.prova.dto.LibroDTO;
import com.example.prova.entities.Libro;

public interface LibriMapper {
    LibroDTO toDTO(Libro libro);

    Libro toEntity(LibroDTO libroDTO, Long id);

    List<LibroDTO> toDTOList(List<Libro> libri);
    
    List<Libro> toEntityList(List<LibroDTO> libroDTOs);
}
