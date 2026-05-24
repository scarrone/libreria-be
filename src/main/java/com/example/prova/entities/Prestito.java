    package com.example.prova.entities;

    import java.sql.Date;
    import java.time.LocalDate;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.PrePersist;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "prestiti")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Prestito {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "utente_id")
        private Utente utente;

        @ManyToOne
        @JoinColumn(name = "libro_id")
        private Libro libro;

        @Column(name = "data_prestito", nullable = false)
        private Date dataPrestito;

        @Column(name = "data_scadenza", nullable = false)
        private Date dataScadenza;

        @Column(name = "data_restituzione", nullable = true)
        private Date dataRestituzione;


        @PrePersist
        protected void onPrePersist() {
            // Se per qualche motivo la data di inizio prestito non è impostata, mettiamo oggi
            if (this.dataPrestito == null) {
                this.dataPrestito = Date.valueOf(LocalDate.now());
            }
            
    LocalDate dataPrestitoLocale = this.dataPrestito.toLocalDate();
        LocalDate dataScadenzaLocale = dataPrestitoLocale.plusDays(30);
        
        // 3. Riconvertiamo il risultato in java.sql.Date per salvarlo nel campo
        this.dataScadenza = Date.valueOf(dataScadenzaLocale);
        }
    }
