package com.example.prova.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestitoDTO {

    private Long utenteId;

    private String libroIsbn;

    private Date dataPrestito;

    private Date dataScadenza;
}
