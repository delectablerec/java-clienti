CREATE TABLE IF NOT EXISTS clienti (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cognome TEXT NOT NULL,
    email TEXT
);

-- Inserisci alcuni dati di esempio
INSERT INTO clienti (nome, cognome, email)
VALUES
('Mario', 'Rossi', 'mario.rossi@example.com'),
('Luca', 'Bianchi', 'luca.bianchi@example.com');