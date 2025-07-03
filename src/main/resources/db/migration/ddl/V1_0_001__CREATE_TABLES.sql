-- create all the tables of adil_db

CREATE TABLE IF NOT EXISTS afg_build(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,
    montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS b13_agricole(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,   montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS hay_rahmani(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,
    montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS iltizamat(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,   montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mawjoudat(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,   montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS investissement(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,
    montant FLOAT,
    commentaire VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS situation_prestataire(
    id BIGSERIAL PRIMARY KEY,
    date_ecrire DATE,
    local VARCHAR(255),
    eau VARCHAR(50),
    electricite VARCHAR(50),
    escalier VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS virements_permanents(
    id BIGSERIAL PRIMARY KEY,
    date_debut DATE,
    date_fin DATE,
    montant FLOAT,
    domiciliation VARCHAR(255),
    beneficiaire VARCHAR(255),
    compte VARCHAR(255),
    adresse VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mission_mensuelle(
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    isCompleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS identifiant(
    id BIGSERIAL PRIMARY KEY,
    numero_compte VARCHAR(50),
    type_compte VARCHAR(255),
    identifiant VARCHAR(255),
    mot_de_passe VARCHAR(255),
    carte_guichet VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS coffre_fort (
    id BIGSERIAL PRIMARY KEY,
    coffre_total FLOAT NOT NULL,
    coffre_name VARCHAR(255),
    coffre_name_Ihm VARCHAR(255),
    coffre_color VARCHAR(255),
    date_derniere_modification DATE
);

CREATE TABLE IF NOT EXISTS credit (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS lkbir (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS khalit (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS adil (
    id BIGSERIAL PRIMARY KEY,
    nom_complet VARCHAR(255),
    montant FLOAT NOT NULL,
    date_sortie DATE,
    date_retour DATE,
    coffre_fort_id INT,
    CONSTRAINT fk_coffre_fort FOREIGN KEY (coffre_fort_id) REFERENCES coffre_fort(id) ON DELETE RESTRICT
);