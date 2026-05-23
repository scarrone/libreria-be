package com.example.prova.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDTO {
    private String title;

    private Long authorId;

    private String genre;

    private Integer publicationYear;

    private String isbn;
}
