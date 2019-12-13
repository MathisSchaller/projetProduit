/**
 * 
 */
package metier;

import java.util.ArrayList;
import java.util.List;

import presentation.FenetrePrincipale;

/**
 * @author petitl
 *
 */
public class CatalogueProduit implements I_Catalogue {

	private List<Produit> lesProduits;
	
	public CatalogueProduit()
	{
		lesProduits = getProduits();
	}
	
	/**
	 * Récupération des produits
	 * 
	 * @return produits Les produits stockés
	 */
	private List<Produit> getProduits()
	{
		List<Produit> produits = new ArrayList<Produit>();
		
		produits.add(new Produit("Twix", 50.5, 50));
		produits.add(new Produit("Mars", 14.52, 500));
		produits.add(new Produit("Kinder Maxi", 10.99, 140));
		
		return produits;
	}

	
	@Override
	public boolean addProduit(I_Produit produit) {

		if(lesProduits.add(new Produit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite())))
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		
		if(lesProduits.add(new Produit(nom, prix, qte)))
		{
			return true;
		}

		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeProduit(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getNomProduits() {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unused")

	@Override
	public double getMontantTotalTTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		
		CatalogueProduit monCatalogue = new CatalogueProduit();
		
		monCatalogue.addProduit(new Produit("Test", 50.12, 5));
		
	}

}
