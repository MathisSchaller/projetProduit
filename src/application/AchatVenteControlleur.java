package application;

import exceptions.ProduitException;
import metier.Catalogue;
import metier.Utilitaire;

/**
 * Classe représentant le controlleur pour l'achat et la vente de produits
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class AchatVenteControlleur 
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
	public AchatVenteControlleur(Catalogue catalogueProduit) 
	{
		this.catalogueProduit = catalogueProduit;
	}
	
	/**
	 * Récupère le nom de tous les produits dans le catalogue
	 * 
	 * @return Un tableau de nom des produits
	 */
	public String[] recupererNomProduits()
	{
		return catalogueProduit.getNomProduits();
	}
	
	/**
	 * Vend la quantité d'un produit
	 * 
	 * @param nom Nom du produit à vendre
	 * @param qte Quantité à vendre
	 */
	public void vendreQuantite(String nom, String qte) throws ProduitException 
	{
		int l_qte = 0;
		
		if(nom == null)
		{
			throw new ProduitException("Aucun produit selectionné");
		}
		
		// Vérification de la quantité
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			if(l_qte <= 0)
			{
				throw new ProduitException("La quantité du produit est inférieur ou égal à 0");
			}
		}
		else
		{
			throw new ProduitException("La quantité du produit n'est pas un nombre");
		}

		if(!catalogueProduit.vendreStock(nom, l_qte))
		{
			throw new ProduitException("Pas assez de stock pour vendre cette quantité");
		}
	}
	
	/**
	 * Acheter la quantité d'un produit
	 * 
	 * @param nom Nom du produit a acheter
	 * @param qte Quantité à acheter
	 */
	public void acheterQuantite(String nom, String qte) throws ProduitException 
	{	
		int l_qte = 0;
		
		if(nom == null)
		{
			throw new ProduitException("Aucun produit selectionné");
		}
		
		// Vérification de la quantité
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			
			if(l_qte <= 0)
			{
				throw new ProduitException("La quantité du produit est inférieur à  0");
			}
		}
		else
		{
			throw new ProduitException("La quantité du produit n'est pas un nombre");
		}

		if(!catalogueProduit.acheterStock(nom, l_qte))
		{
			throw new ProduitException("Pas assez de stock pour acheter cette quantité");
		}
	}
}
