            package com.example.prova.services;

            import com.example.prova.repositories.PrestitiRepository;

        import java.sql.Date;
        import java.util.List;

            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.stereotype.Service;

            import com.example.prova.dto.PrestitoDTO;
            import com.example.prova.entities.Prestito;
            import com.example.prova.exception.ResourceNotFoundException;
            import com.example.prova.mapper.PrestitiMapper;
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
                libroService.getLibroByIsbn(prestitoDTO.getLibroIsbn());
                utentiRepository.findById(prestitoDTO.getUtenteId()).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));

                    return prestitiMapper.toEntity(prestitoDTO, null);
                }

                public List<Prestito> getPrestiti() {
                    return prestitiRepository.findAll();
                }

                public Prestito getPrestitoById(Long id) {
                    // Logica per ottenere un prestito specifico per ID
                    return prestitiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestito non trovato"));
                }

                public void updatePrestito(Date dataScadenza, Date dataRestituzione, Long id) {

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

                    prestitiRepository.save(prestito);
                }

                public void deletePrestito(Long id) {
                    Prestito prestito = getPrestitoById(id);
                    prestitiRepository.delete(prestito);
                }
            }
