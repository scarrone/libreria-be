INSERT INTO autori (nome, cognome, data_nascita, nazionalita) 
VALUES ('Alessandro', 'Manzoni', '1785-03-07', 'Italiana');

INSERT INTO utenti (nome, cognome, email, data_iscrizione) 
VALUES ('Mario', 'Rossi', 'mario.rossi@email.com', '2026-05-24');

INSERT INTO libri (titolo, autore_id, genere, anno_pubblicazione, isbn) 
VALUES ('I Promessi Sposi', 1, 'Romanzo Storico', 1827, '9788804721133');

-- INSERT INTO prestiti (utente_id, libro_id)
-- VALUES (1, 1);