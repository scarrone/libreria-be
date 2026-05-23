package com.example.prova.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.prova.dto.PrestitoDTO;
import com.example.prova.entities.Libro;
import com.example.prova.entities.Prestito;
import com.example.prova.entities.Utente;

@Component
public class PrestitiMapperImpl implements PrestitiMapper {

    @Override
    public PrestitoDTO toDTO(Prestito prestito) {
        if (prestito == null) {
            return null;
        }

        PrestitoDTO prestitoDTO = new PrestitoDTO();
        prestitoDTO.setUtenteId(prestito.getUtente().getId());
        prestitoDTO.setLibroIsbn(prestito.getLibro().getIsbn());
        prestitoDTO.setDataPrestito(prestito.getDataPrestito());
        prestitoDTO.setDataScadenza(prestito.getDataScadenza());
        return prestitoDTO;
    }

    @Override
    public Prestito toEntity(PrestitoDTO prestitoDTO, Long id) {
        if (prestitoDTO == null) {
            return null;
        }

        Prestito prestito = new Prestito();
        prestito.setUtente(new Utente());
        prestito.setLibro(new Libro());
        prestito.setId(id);
        prestito.getUtente().setId(prestitoDTO.getUtenteId());
        prestito.getLibro().setIsbn(prestitoDTO.getLibroIsbn());
        prestito.setDataPrestito(prestitoDTO.getDataPrestito());
        prestito.setDataScadenza(prestitoDTO.getDataScadenza());
        return prestito;
    }

    @Override
    public List<PrestitoDTO> toDTOList(List<Prestito> prestiti) {

        List<PrestitoDTO> dtoList = new java.util.ArrayList<>();
        for (Prestito prestito : prestiti) {
            dtoList.add(toDTO(prestito));
        }
        return dtoList;
    }

    @Override
    public List<Prestito> toEntityList(List<PrestitoDTO> prestitiDTOs) {

        List<Prestito> entityList = new java.util.ArrayList<>();
        for (PrestitoDTO prestitoDTO : prestitiDTOs) {
            entityList.add(toEntity(prestitoDTO, null));
        }
        return entityList;
    }
    
}
