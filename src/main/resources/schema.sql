-- Rimuoviamo le tabelle se esistono già per ripartire puliti ad ogni avvio
DROP TABLE IF EXISTS prestiti;
DROP TABLE IF EXISTS libri;
DROP TABLE IF EXISTS autori;
DROP TABLE IF EXISTS utenti;

CREATE TABLE autori(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cognome VARCHAR(50) NOT NULL,
	data_nascita DATE NOT NULL,
	nazionalita VARCHAR(50)
);

CREATE TABLE utenti(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cognome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	data_iscrizione DATE NOT NULL
);

CREATE  TABLE libri(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	titolo VARCHAR(120) NOT NULL,
	autore_id INT,
	genere VARCHAR(100) NOT NULL,
	anno_pubblicazione BIGINT NOT NULL,
	isbn VARCHAR(13) UNIQUE,
	CONSTRAINT fk_autore
    FOREIGN KEY (autore_id)
    REFERENCES autori(id)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE prestiti(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	utente_id BIGINT NOT NULL,
	libro_id BIGINT NOT NULL,
	data_prestito DATE NOT NULL,
	data_scadenza DATE NOT NULL,
	data_restituzione DATE,
	CONSTRAINT fk_libro
	FOREIGN KEY(libro_id)
	references libri(id)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_utente
	FOREIGN KEY(utente_id)
	references utenti(id)
	ON UPDATE CASCADE ON DELETE CASCADE
);