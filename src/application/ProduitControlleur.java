package application;

import exceptions.ProduitException;
import metier.Catalogue;
import metier.Utilitaire;
import presentation.FenetreNouveauProduit;
import presentation.FenetreSuppressionProduit;

/**
 * Classe repr�sentant le controlleur pour l'ajout et la suppression de produits
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class ProduitControlleur 
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
	public ProduitControlleur(Catalogue catalogueProduit) 
	{
		this.catalogueProduit = catalogueProduit;
	}
	
	/**
	 * Ajoute un produit au catalogue
	 * 
	 * @param nom Nom du produit a ajouter
	 * @param prix Prix du produit a ajouter
	 * @param qte Quantit� du produit a ajouter
	 */
	public void ajouterProduit(String nom, String prix, String qte) throws ProduitException 
	{
		double l_prix = 0.0;
		int l_qte = 0;
		
		if(nom.isEmpty())
		{
			throw new ProduitException("Le nom du produit est vide");
		}
		
		// V�rification du prix
		if(Utilitaire.isDouble(prix.replaceAll(",", ".")))
		{
			l_prix = Double.parseDouble(prix.replaceAll(",", "."));
			
			if(l_prix <= 0)
			{
				throw new ProduitException("Le prix du produit est inf�rieur ou �gal � 0");
			}
		}
		else
		{
			throw new ProduitException("Le prix du produit n'est pas un nombre");
		}
		
		// V�rification de la quantit�
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			if(l_qte < 0)
			{
				throw new ProduitException("La quantit� du produit est inf�rieur�� 0");
			}
		}
		else
		{
			throw new ProduitException("La quantit� du produit n'est pas un nombre");
		}

		if(!catalogueProduit.addProduit(nom, l_prix, l_qte))
		{
			throw new ProduitException("Le produit existe d�j�");
		}
	}
	
	/**
	 * Supprime un produit du catalogue
	 * 
	 * @param produit Le nom du produit � supprimer
	 */
	public void supprimerProduit(String nomProduit) throws ProduitException 
	{
		if(nomProduit == null)
		{
			throw new ProduitException("Pas de produit selectionn�");
		}
		
		if(!catalogueProduit.removeProduit(nomProduit))
		{
			throw new ProduitException("Le produit n'a pas pu �tre supprim�");
		}
	}
	
	/**
	 * R�cup�re le nom de tous les produits dans le catalogue
	 * 
	 * @return un tableau de nom des produits
	 */
	public String[] recupererNomProduits()
	{
		return catalogueProduit.getNomProduits();
	}
}
