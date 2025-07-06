ALTER TABLE coffre_fort
DROP COLUMN commentaire;

ALTER TABLE credit
    ADD COLUMN commentaire VARCHAR(255);

ALTER TABLE lkbir
    ADD COLUMN commentaire VARCHAR(255);

ALTER TABLE khalit
    ADD COLUMN commentaire VARCHAR(255);

ALTER TABLE adil
    ADD COLUMN commentaire VARCHAR(255);
