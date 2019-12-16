package application;

import metier.Catalogue;
import presentation.FenetreNouveauProduit;
import presentation.FenetreSuppressionProduit;

public class ProduitControlleur {
	
	private Catalogue cat;

	/**
	 * Constructeur du controlleur
	 * @param cat Le catalogue de produits
	 */
	public ProduitControlleur(Catalogue cat) {
		this.cat = cat;
	}
	
	/**
	 * Ouvre la fenetre de suppression
	 */
	public void demandeSuppression() {
		// Listes des produits
		String[] tabProduits = cat.getNomProduits();
		
		//Ouverture de la fenêtre de suppression
		new FenetreSuppressionProduit(tabProduits);
	}
	
	/**
	 * Supprime un produit du catalogue
	 * @param produit Le produit a supprimer
	 */
	public void supprimerProduit(String produit) {		
		// Suppression du produit
		cat.removeProduit(produit);
		
		// Ouvre une message box de confirmation
	}

	/**
	 * Ouvre la fenetre d'ajout de produits
	 */
	public void demandeCreation() {
		//Ouverture de la fenêtre de suppression
		new FenetreNouveauProduit();
	}
	
	/**
	 * Ajoute un produit au catalogue
	 * @param nom Nom du produit a ajouter
	 * @param prix Prix du produit a ajouter
	 * @param qte Quantité du produit a ajouter
	 */
	public void ajouterProduit(String nom, double prix, int qte) {		
		// Ajout du produit
		cat.addProduit(nom, prix, qte);
	}

}
