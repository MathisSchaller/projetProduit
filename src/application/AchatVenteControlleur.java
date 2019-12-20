package application;

import exceptions.ProduitException;
import metier.Catalogue;
import metier.Utilitaire;

/**
 * @author Mathis Schaller
 * @author LoÔøΩc Petit
 *
 * Classe repr√©sentant le controlleur pour l'achat et la vente de produits
 */
public class AchatVenteControlleur {
	
	/**
	 * Catalogue de produits
	 */
	private Catalogue catalogueProduit;

	/**
	 * Constructeur du controlleur
	 * 
	 * @param catalogueProduit Le catalogue de produits
	 */
	public AchatVenteControlleur(Catalogue catalogueProduit) {
		this.catalogueProduit = catalogueProduit;
	}
	
	/**
	 * R√©cup√®re le nom de tous les produits dans le catalogue
	 * 
	 * @return Un tableau de nom des produits
	 */
	public String[] recupererNomProduits()
	{
		return catalogueProduit.getNomProduits();
	}
	
	/**
	 * Ajoute un produit au catalogue
	 * 
	 * @param nom Nom du produit a ajouter
	 * @param qte Quantit√© du produit a ajouter
	 */
	public void vendreQuantite(String nom, String qte) throws ProduitException {

		int l_qte = 0;
		
		if(nom == null)
		{
			throw new ProduitException("Aucun produit selectionnÈ");
		}
		
		
		// V√©rification de la quantit√©
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			if(l_qte <= 0)
			{
				throw new ProduitException("La quantitÈ du produit est infÈrieur ou Ègal ‡ 0");
			}
		}
		else
		{
			throw new ProduitException("La quantitÈ du produit n'est pas un nombre");
		}

		if(!catalogueProduit.vendreStock(nom, l_qte))
		{
			throw new ProduitException("Pas assez de stock pour vendre cette quantitÈ");
		}
	}
	
	/**
	 * Supprime un produit du catalogue
	 * 
	 * @param nom Nom du produit a supprimer
	 * @param qte Quantit√© √† acheter
	 */
	public void acheterQuantite(String nom, String qte) throws ProduitException {
		
		int l_qte = 0;
		
		if(nom == null)
		{
			throw new ProduitException("Aucun produit selectionnÈ");
		}
		
		
		// V√©rification de la quantit√©
		if(Utilitaire.isInteger(qte))
		{
			l_qte = Integer.parseInt(qte);
			
			if(l_qte <= 0)
			{
				throw new ProduitException("La quantitÈ du produit est infÈrieur ‡† 0");
			}
		}
		else
		{
			throw new ProduitException("La quantitÈ du produit n'est pas un nombre");
		}

		if(!catalogueProduit.acheterStock(nom, l_qte))
		{
			throw new ProduitException("Pas assez de stock pour acheter cette quantitÈ");
		}
	}
}
