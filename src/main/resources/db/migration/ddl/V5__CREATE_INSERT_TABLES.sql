CREATE TABLE IF NOT EXISTS adil_2 (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    commentaire VARCHAR(255),
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS adil_3 (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    commentaire VARCHAR(255),
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);

INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (5, 1.0, 'عادل 2', 'عادل 2', '#f74924', NULL)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (6, 1.0, 'عادل 3', 'عادل 3', '#2ce10c', NULL)
    ON CONFLICT (id) DO NOTHING;