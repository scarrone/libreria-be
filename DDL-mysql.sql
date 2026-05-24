CREATE TABLE autori(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cognome VARCHAR(50) NOT NULL,
	data_nascita DATE NOT NULL,
	nazionalita VARCHAR(50)
);

CREATE  TABLE libri(
	id INT PRIMARY KEY AUTO_INCREMENT,
	titolo VARCHAR(120) NOT NULL,
	autore_id INT,
	genere VARCHAR(100) NOT NULL,
	anno_pubblicazione INT NOT NULL,
	isbn VARCHAR(13) UNIQUE,
	CONSTRAINT fk_autore
    FOREIGN KEY (autore_id)
    REFERENCES autori(id)
    ON UPDATE CASCADE ON DELETE SET NULL
);
    
CREATE TABLE utenti(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cognome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	data_iscrizione DATE NOT NULL
);

CREATE TABLE prestiti(
	id INT PRIMARY KEY AUTO_INCREMENT,
	utente_id INT NOT NULL,
	libro_id INT NOT NULL,
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


DELIMITER $$
CREATE TRIGGER prima_di_inserire_prestito
BEFORE INSERT ON prestiti
FOR EACH ROW
BEGIN
    -- 1. Se il frontend non ha inviato la data del prestito, impostiamo quella di oggi
    IF NEW.data_prestito IS NULL THEN
        SET NEW.data_prestito = CURDATE();
    END IF;

    -- 2. Calcoliamo la data di scadenza aggiungendo 30 giorni a data_prestito
    SET NEW.data_scadenza = DATE_ADD(NEW.data_prestito, INTERVAL 30 DAY);
END$$
DELIMITER ;

-- per attivare o disattivare i controlli sulle foreign keys
SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE utenti 
MODIFY COLUMN id INT AUTO_INCREMENT;


INSERT INTO autori (nome, cognome, data_nascita, nazionalita) 
VALUES ('Alessandro', 'Manzoni', '1785-03-07', 'Italiana');

INSERT INTO utenti (nome, cognome, email, data_iscrizione) 
VALUES ('Mario', 'Rossi', 'mario.rossi@email.com', '2026-05-24');

INSERT INTO libri (titolo, autore_id, genere, anno_pubblicazione, isbn) 
VALUES ('I Promessi Sposi', 1, 'Romanzo Storico', 1827, '9788804721133');

INSERT INTO prestiti (utente_id, libro_id)
VALUES (1, 1);

select * from prestiti;