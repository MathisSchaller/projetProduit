package application;

import metier.Catalogue;

/**
 * Classe qui repr�sente le controlleur central de l'application
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class ApplicationControlleur 
{
	private Catalogue catalogueProduit;
	private ProduitControlleur produitControlleur;
	private AchatVenteControlleur achatVenteControlleur;
	private StockControlleur stockControlleur;
	
	/**
	 * Constructeur de la classe
	 */
	public ApplicationControlleur()
	{
		catalogueProduit = new Catalogue();
		produitControlleur = (new ProduitControlleur(catalogueProduit));
		achatVenteControlleur = new AchatVenteControlleur(catalogueProduit);
		stockControlleur = new StockControlleur(catalogueProduit);
	}
	
	/**
	 * R�cup�re le catalogue de produits
	 * 
	 * @return Le catalogue de produits
	 */
	public Catalogue getCatalogueProduit() 
	{
		return this.catalogueProduit;
	}

	/**
	 * R�cup�re le controlleur d'ajout et de suppression de produits
	 * 
	 * @return Le controlleur produitControlleur
	 */
	public ProduitControlleur getProduitControlleur() 
	{
		return this.produitControlleur;
	}
	
	/**
	 * R�cup�re le controlleur d'achat et de vente de produits
	 * 
	 * @return Le controlleur achatVenteControlleur
	 */
	public AchatVenteControlleur getAchatVenteControlleur() 
	{
		return this.achatVenteControlleur;
	}
	
	/**
	 * R�cup�re le controlleur de l'affichage des informations des produits
	 * 
	 * @return Le controlleur stockControlleur
	 */
	public StockControlleur getStockControlleur() 
	{
		return this.stockControlleur;
	}
}
