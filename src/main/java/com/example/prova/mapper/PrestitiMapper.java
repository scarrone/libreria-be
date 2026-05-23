package com.example.prova.mapper;

import java.util.List;

import com.example.prova.dto.PrestitoDTO;
import com.example.prova.entities.Prestito;

public interface PrestitiMapper {
    PrestitoDTO toDTO(Prestito prestito);

    Prestito toEntity(PrestitoDTO prestitoDTO, Long id);

    List<PrestitoDTO> toDTOList(List<Prestito> prestiti);
    
    List<Prestito> toEntityList(List<PrestitoDTO> prestitoDTOs);    
}
