CREATE TABLE Produits
(
	id NUMBER(5),
	nom VARCHAR(60),
	prix NUMBER(7,2),
	quantite NUMBER(5),
	CONSTRAINT pk_Produits PRIMARY KEY (id),
	CONSTRAINT nn_nom_Produits CHECK (nom IS NOT NULL),
	CONSTRAINT un_nom_Produits UNIQUE (nom),
	CONSTRAINT nn_prix_Produits CHECK (prix IS NOT NULL),
	CONSTRAINT ck_prix_Produits CHECK (prix > 0),
	CONSTRAINT nn_quantite_Produits CHECK (quantite IS NOT NULL),
	CONSTRAINT ck_quantite_Produits CHECK (quantite >= 0)
);

INSERT INTO Produits (id, nom, prix, quantite) VALUES (4, 'Twix', 15.56, 50);

CREATE SEQUENCE seqProduits
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE nouveauProduit (p_nom IN Produits.nom%TYPE,
										   	  p_prix IN Produits.prix%TYPE,
										   	  p_quantite IN Produits.quantite%TYPE) IS 
BEGIN
	INSERT INTO Produits (id, nom, prix, quantite) VALUES (seqProduits.NEXTVAL, p_nom, p_prix, p_quantite);
END;

call nouveauProduit('Twix', 15.99, 50);