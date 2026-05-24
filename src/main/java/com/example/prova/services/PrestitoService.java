            package com.example.prova.services;

            import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prova.dto.PrestitoDTO;
import com.example.prova.entities.Libro;
import com.example.prova.entities.Prestito;
import com.example.prova.entities.Utente;
import com.example.prova.exception.ResourceNotFoundException;
import com.example.prova.mapper.PrestitiMapper;
import com.example.prova.repositories.PrestitiRepository;
import com.example.prova.repositories.UtentiRepository;

            @Service
            public class PrestitoService {

                private final PrestitiRepository prestitiRepository;

                private final LibroService libroService;

                private final PrestitiMapper prestitiMapper;

                private final UtentiRepository utentiRepository;

                PrestitoService(PrestitiRepository prestitiRepository, LibroService libroService, PrestitiMapper prestitiMapper, UtentiRepository utentiRepository) {
                    this.prestitiRepository = prestitiRepository;
                    this.libroService = libroService;
                    this.prestitiMapper = prestitiMapper;
                    this.utentiRepository = utentiRepository;
                }
                
                public Prestito createPrestito(PrestitoDTO prestitoDTO) {
                Libro libro = libroService.getLibroByIsbn(prestitoDTO.getLibroIsbn());
                Utente utente =utentiRepository.findById(prestitoDTO.getUtenteId()).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));

                Prestito prestito = prestitiMapper.toEntity(prestitoDTO, null);
                prestito.setLibro(libro);
                prestito.setUtente(utente);
                if(prestitoDTO.getDataPrestito() == null){
                    prestito.setDataPrestito(new Date(System.currentTimeMillis()));
                }else{
                    prestito.setDataPrestito(prestitoDTO.getDataPrestito());
                }
                
                if(prestitoDTO.getDataScadenza() == null){
                    prestito.setDataScadenza(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000));
                }else{
                    prestito.setDataScadenza(prestitoDTO.getDataScadenza());
                }

                    return prestitiRepository.save(prestito);
                }

                public List<Prestito> getPrestiti() {
                    return prestitiRepository.findAll();
                }

                public Prestito getPrestitoById(Long id) {
                    // Logica per ottenere un prestito specifico per ID
                    return prestitiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestito non trovato"));
                }

                public Prestito updatePrestito(Date dataScadenza, Date dataRestituzione, Long id) {

                    if(id == null) {
                        throw new IllegalArgumentException("Indicare l'id del prestito da aggiornare");
                    }

                    Prestito prestito = getPrestitoById(id);
                    if(dataRestituzione != null) {
                    prestito.setDataRestituzione(dataRestituzione);
                    }
                    if(dataScadenza != null) {
                        prestito.setDataScadenza(dataScadenza);
                    }

                    return prestitiRepository.save(prestito);
                }

                public void deletePrestito(Long id) {
                    Prestito prestito = getPrestitoById(id);
                    prestitiRepository.delete(prestito);
                }
            }
