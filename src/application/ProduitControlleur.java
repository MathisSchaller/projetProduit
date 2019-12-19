package application;

import metier.Catalogue;
import metier.Utilitaire;
import presentation.FenetreNouveauProduit;
import presentation.FenetreSuppressionProduit;

public class ProduitControlleur {
	
	public final static int PRODUIT_CREE = 0;
	public final static int PRODUIT_ERREUR_EXISTANT = 1;
	public final static int PRODUIT_ERREUR_NOM = 2;
	public final static int PRODUIT_ERREUR_PRIX = 3;
	public final static int PRODUIT_ERREUR_QUANTITE = 4;
	
	private Catalogue catalogueProduit;

	/**
	 * Constructeur du controlleur
	 * 
	 * @param catalogueProduit Le catalogue de produits
	 */
	public ProduitControlleur(Catalogue catalogueProduit) {
		this.catalogueProduit = catalogueProduit;
	}
	
	/**
	 * Ajoute un produit au catalogue
	 * 
	 * @param nom Nom du produit a ajouter
	 * @param prix Prix du produit a ajouter
	 * @param qte Quantit√© du produit a ajouter
	 */
	public int ajouterProduit(String nom, String prix, String qte) {
		
		double l_prix = 0.0;
		int l_qte = 0;
		
		if(nom.isEmpty())
		{
			return ProduitControlleur.PRODUIT_ERREUR_NOM;
		}
		
		// VÈrification du prix
		if(Utilitaire.isDouble(prix.replaceAll(",", ".")))
		{
			l_prix = Double.parseDouble(prix.replaceAll(",", "."));
			
			if(l_prix <= 0)
			{
				System.out.println("NÈgatif");
				return ProduitControlleur.PRODUIT_ERREUR_PRIX;
			}
		}
		else
		{
			System.out.println("Pas parsable");
			return ProduitControlleur.PRODUIT_ERREUR_PRIX;
		}
		
		// VÈrification de la quantitÈ
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			if(l_qte < 0)
			{
				System.out.println("NÈgatif");
				return ProduitControlleur.PRODUIT_ERREUR_QUANTITE;
			}
		}
		else
		{
			System.out.println("Pas parsable");
			return ProduitControlleur.PRODUIT_ERREUR_QUANTITE;
		}

		if(catalogueProduit.addProduit(nom, l_prix, l_qte))
		{
			return ProduitControlleur.PRODUIT_CREE;
		}
		else
		{
			return ProduitControlleur.PRODUIT_ERREUR_EXISTANT;
		}
	}
	
	/**
	 * Supprime un produit du catalogue
	 * @param produit Le produit a supprimer
	 */
	public void supprimerProduit(String produit) {	
		// Suppression du produit
		catalogueProduit.removeProduit(produit);
		
		// Ouvre une message box de confirmation
	}
}
