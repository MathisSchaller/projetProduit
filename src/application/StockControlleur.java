package application;

import metier.Catalogue;

/**
 * Classe représentant le controlleur pour l'affichage des informations des produits
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class StockControlleur 
{
	/**
	 * Catalogue de produits
	 */
	private Catalogue catalogueProduit;
	
	/**
	 * Constructeur du controlleur
	 * 
	 * @param catalogueProduit Le catalogue de produits
	 */
	public StockControlleur(Catalogue catalogueProduit) 
	{
		this.catalogueProduit = catalogueProduit;
	}
	
	/**
	 * Permet de récupèrer les informations sur le stock de produits
	 * 
	 * @return Les informations sur le stock de produits
	 */
	public String afficherStock()
	{
		return catalogueProduit.toString();
	}
}
