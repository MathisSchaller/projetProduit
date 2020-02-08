package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import metier.Catalogue;
import metier.I_Catalogue;

/**
 * Classe qui repr�sente le controlleur s'occupant du catalogue
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class CatalogueControlleur 
{
	/**
	 * Catalogue de produit utilis�
	 */
	private Catalogue catalogueProduit;
	
	/**
	 * Controlleur s'occupant de g�rer l'ajout et la suppression de produits
	 */
	private ProduitControlleur produitControlleur;
	
	/**
	 * Controlleur s'occupant de g�rer l'achat et la vente de produits
	 */
	private AchatVenteControlleur achatVenteControlleur;
	
	/**
	 * Controlleur s'occupant de g�rer l'affichage des stocks
	 */
	private StockControlleur stockControlleur;
	
	/**
	 * Liste des catalogues existants
	 */
	private List<I_Catalogue> lesCatalogues;
	
	/**
	 * Constructeur de la classe
	 */
	public CatalogueControlleur() {}

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
	
	/**
	 * R�cup�re le nom des catalogues par ordre alphab�tique
	 * 
	 * @return Le nom des catalogues
	 */
	public String[] recupererNomCatalogues()
	{
		lesCatalogues = Catalogue.getAll();
		
		List<String> nomCatalogues = new ArrayList<String>();
				
		// On parcours la liste avec un iterateur
		Iterator<I_Catalogue> it = lesCatalogues.iterator();
		
		int i;
		
		for(i = 0; it.hasNext(); i++) 
		{
			I_Catalogue catalogueTemp = it.next();
			
			nomCatalogues.add(catalogueTemp.getNom());
		}
		
		nomCatalogues.sort(String.CASE_INSENSITIVE_ORDER);
		
		return nomCatalogues.toArray(new String[0]);
	}
	
	/**
	 * R�cup�re les d�tails concernant les catalogues par ordre alphab�tique
	 * 
	 * @return Les d�tails des catalogues
	 */
	public String[] recupererDetailsCatalogue()
	{		
		lesCatalogues = Catalogue.getAll();
		
		List<String> detailsCatalogues = new ArrayList<String>();
				
		// On parcours la liste avec un iterateur
		Iterator<I_Catalogue> it = lesCatalogues.iterator();
		
		int i;
		
		for(i = 0; it.hasNext(); i++) 
		{
			I_Catalogue catalogueTemp = it.next();
			
			detailsCatalogues.add(catalogueTemp.getNom() + " : " + catalogueTemp.getNombreProduits() + " produits");
		}
		
		detailsCatalogues.sort(String.CASE_INSENSITIVE_ORDER);
		
		return detailsCatalogues.toArray(new String[0]);
	}
	
	/**
	 * S�lectionne le catalogue � utiliser et initialise les controlleurs utiles pour la fen�tre principale
	 * 
	 * @param nomCatalogue Le nom du catalogue
	 */
	public void selectionnerCatalogue(String nomCatalogue)
	{
		catalogueProduit = new Catalogue(nomCatalogue);
		produitControlleur = new ProduitControlleur(catalogueProduit);
		achatVenteControlleur = new AchatVenteControlleur(catalogueProduit);
		stockControlleur = new StockControlleur(catalogueProduit);
	}

	/**
	 * Ajoute un catalogue
	 * 
	 * @param nom Nom du catalogue
	 */
	public boolean ajouterCatalogue(String nom) 
	{
		Catalogue ajoutCatalogue = new Catalogue(nom);
		if(ajoutCatalogue.save())
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Supprime un catalogue
	 * 
	 * @param nom Nom du catalogue
	 */
	public boolean supprimerCatalogue(String nom)
	{
		Catalogue ajoutCatalogue = new Catalogue(nom);
		if(ajoutCatalogue.delete())
		{
			return true;
		}
		
		return false;
	}
}
