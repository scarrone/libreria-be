package com.example.prova.entities;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libri")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo", length = 120, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore author;

    @Column(name = "genere", length = 100)
    private String genre;

    @Column(name = "anno_pubblicazione", nullable = false)
    private Integer publicationYear;

    @Column(name = "isbn", unique = true, nullable = false)
    @Check(constraints = "LENGTH(isbn) = 13")
    private String isbn;
}
