CREATE TABLE Catalogues
(
	id NUMBER(5),
	nom VARCHAR(60),
	CONSTRAINT pk_Catalogues PRIMARY KEY (id),
	CONSTRAINT nn_nom_Catalogues CHECK (nom IS NOT NULL),
	CONSTRAINT un_nom_Catalogues UNIQUE (nom)
);

CREATE TABLE Produits
(
	id NUMBER(5),
	nom VARCHAR(60),
	prix NUMBER(7,2),
	quantite NUMBER(5),
	idCatalogue NUMBER(5),
	CONSTRAINT pk_Produits PRIMARY KEY (id),
	CONSTRAINT nn_nom_Produits CHECK (nom IS NOT NULL),
	CONSTRAINT nn_prix_Produits CHECK (prix IS NOT NULL),
	CONSTRAINT ck_prix_Produits CHECK (prix > 0),
	CONSTRAINT nn_quantite_Produits CHECK (quantite IS NOT NULL),
	CONSTRAINT ck_quantite_Produits CHECK (quantite >= 0),
	CONSTRAINT fk_idCatalogue_Produits FOREIGN KEY (idCatalogue) REFERENCES Catalogues(id) ON DELETE CASCADE,
	CONSTRAINT nn_idCatalogue_Produits CHECK (idCatalogue IS NOT NULL)
);

CREATE SEQUENCE seqCatalogue
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE nouveauCatalogue (p_nom IN Catalogues.nom%TYPE) IS 
BEGIN
	INSERT INTO Catalogues (id, nom) VALUES (seqCatalogue.NEXTVAL, p_nom);
END;

CREATE SEQUENCE seqProduits
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE FUNCTION findIdCatalogue (p_nom IN Catalogues.nom%TYPE) RETURN Catalogues.id%TYPE IS
	v_id Catalogues.id%TYPE;
BEGIN
	SELECT id INTO v_id
	FROM Catalogues
	WHERE nom = p_nom;
	RETURN v_id;
END;

CREATE OR REPLACE FUNCTION checkProduitInCatalogue (p_idCatalogue IN Catalogues.id%TYPE, p_nom IN Produits.nom%TYPE) RETURN NUMBER IS
	v_nb NUMBER;
BEGIN
	SELECT count(*) INTO v_nb
	FROM Produits
	WHERE nom = p_nom
	AND idCatalogue = p_idCatalogue;
	RETURN v_nb;
END;

CREATE OR REPLACE PROCEDURE nouveauProduit (p_nom IN Produits.nom%TYPE, p_prix IN Produits.prix%TYPE, p_quantite IN Produits.quantite%TYPE, p_nomCatalogue IN Catalogues.nom%TYPE) IS
BEGIN
	IF checkProduitInCatalogue(findIdCatalogue(p_nomCatalogue), p_nom) > 0 THEN
		RAISE_APPLICATION_ERROR(-20001, 'Le produit existe déjà');
	ELSE
		INSERT INTO Produits (id, nom, prix, quantite, idCatalogue) VALUES(seqProduits.NEXTVAL, p_nom, p_prix, p_quantite, findIdCatalogue(p_nomCatalogue));
	END IF;
END;