package metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import presentation.FenetrePrincipale;

/**
 * @author Mathis Schaller
 * @author Loï¿½c Petit
 *
 * Classe reprÃ©sentant un catalogue de produit
 */
public class Catalogue implements I_Catalogue {

	/**
	 * Liste des produits
	 */
	private List<I_Produit> lesProduits;
	
	/**
	 * Constructeur de la classe CatalogueProduit
	 */
	public Catalogue() {
		
		lesProduits = getProduits();
	}
	
	/**
	 * Rï¿½cupï¿½ration des produits
	 * 
	 * @return Les produits stockï¿½s
	 */
	private List<I_Produit> getProduits() {
		List<I_Produit> produits = new ArrayList<I_Produit>();
		return produits;
	}

	/**
	 * Ajouter un produit
	 * 
	 * @param produit Le produit ï¿½ ajouter
	 * @return True ou false en fonction de si le produit a ï¿½tï¿½ ajoutï¿½
	 */
	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit != null)
		{
			if(!existe(produit.getNom()) && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) {
				lesProduits.add(new Produit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite()));
				return true;
			}	
		}

		return false;
	}

	/**
	 * Ajouter un produit
	 * 
	 * @param nom Nom du produit
	 * @param prix Prix du produit
	 * @param qte Quantitï¿½ du produit
	 * @return True ou false en fonction de si le produit a ï¿½tï¿½ ajoutï¿½
	 */
	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		if(!existe(nom.trim()) && prix > 0 && qte >= 0) {
			lesProduits.add(new Produit(nom, prix, qte));
			return true;
		}

		return false;
	}

	/**
	 * Ajouter une liste de produits
	 * 
	 * @param l Liste de produits
	 * @return Le nombre de produit ajoutï¿½s
	 */
	@Override
	public int addProduits(List<I_Produit> l) {

		int produitAjoute = 0;
		
		if(l != null)
		{
			Iterator<I_Produit> it = l.iterator();
			
			while(it.hasNext()) {
				I_Produit produitTemp = it.next();
				
				if(!existe(produitTemp.getNom()) && produitTemp.getPrixUnitaireHT() > 0 && produitTemp.getQuantite() >= 0) {
					lesProduits.add(new Produit(produitTemp.getNom(), produitTemp.getPrixUnitaireHT(), produitTemp.getQuantite()));
					produitAjoute++;
				}
			}
		}
		
		return produitAjoute;
	}

	/**
	 * Enlever un produit
	 * 
	 * @param nom Nom du produit
	 * @return True ou false en fonction de si le produit a ï¿½tï¿½ enlevï¿½
	 */
	@Override
	public boolean removeProduit(String nom) {
		int indexProduit = this.trouverIndex(nom);
		
		if(indexProduit > -1)
		{
			lesProduits.remove(indexProduit);
			return true;
		}
		
		return false;
	}

	/**
	 * Acheter du stock du produit
	 * 
	 * @param nomProduit Nom du produit
	 * @param qteAchetee Quantitï¿½ du produit ï¿½ ajouter
	 * @return True ou false en fonction de si la quantitï¿½ du produit a ï¿½tï¿½ modifiï¿½e
	 */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(qteAchetee > 0)
		{
			int indexProduit = this.trouverIndex(nomProduit);
			
			if(indexProduit > -1)
			{
				if(lesProduits.get(indexProduit).ajouter(qteAchetee))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Vendre du stock du produit
	 * 
	 * @param nomProduit Nom du produit
	 * @param qteVendue Quantitï¿½ du produit ï¿½ enlever
	 * @return True ou false en fonction de si la quantitï¿½ du produit a ï¿½tï¿½ modifiï¿½e
	 */
	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if(qteVendue > 0)
		{
			int indexProduit = this.trouverIndex(nomProduit);
			
			if(indexProduit > -1)
			{
				if(lesProduits.get(indexProduit).enlever(qteVendue))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Rï¿½cupï¿½re les noms de tous les produits
	 * 
	 * @return Tableau contenant le nom des produits
	 */
	@Override
	public String[] getNomProduits() {
		// Crï¿½ation d'un tableau de String qui prend la taille de la liste des produits
		String[] nomProduits = new String[lesProduits.size()];
		// On parcours la liste avec un iterateur
		Iterator<I_Produit> it = lesProduits.iterator();
		int i;
		
		for(i = 0; it.hasNext(); i++) {
			I_Produit produitTemp = it.next();
			
			nomProduits[i] = produitTemp.getNom();
		}
		Arrays.sort(nomProduits);
		return nomProduits;
	}
	
	/**
	 * Vï¿½rifie si le nom du produit existe dans la liste des produits
	 * 
	 * @param nomProduit Nom du produit
	 * @return True ou false en fonction l'existence du produit
	 */
	private boolean existe(String nomProduit) {
		String[] nomsProduits = this.getNomProduits();
		
		for(String nom : nomsProduits) {
			if(nom.equals(nomProduit)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Cherche l'index d'un produit avec son nom
	 * 
	 * @param nomProduit Le nom du produit
	 * @return L'index du produit ou -1 si le produit n'existe pas
	 */
	private int trouverIndex(String nomProduit)
	{
		Iterator<I_Produit> it = lesProduits.iterator();
		int i;
		
		for(i = 0; it.hasNext(); i++)
		{
			I_Produit produitTemp = it.next();
			if(produitTemp.getNom() == nomProduit)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Rï¿½cupï¿½re le montant TTC total de tous les produits (en prenant en compte le stock)
	 * 
	 * @return Le montant total TTC
	 */
	@Override
	public double getMontantTotalTTC() {
		Iterator<I_Produit> it = lesProduits.iterator();
		double montantTotalTTC = 0.0;
		
		while(it.hasNext()) {
			I_Produit produitTemp = it.next();
			
			montantTotalTTC += produitTemp.getPrixStockTTC();
		}
		
		return Utilitaire.arrondirA2decimal(montantTotalTTC);
	}

	@Override
	/**
	 * Vide la liste des produits
	 */
	public void clear() {
		lesProduits.clear();
	}
	
	@Override
	/**
	 * Affiche le catalogue
	 * 
	 * @return Les informations sur les produits dans le catalogue
	 */
	public String toString() {
		String message = "";
		
		Iterator<I_Produit> it = lesProduits.iterator();
		
		while(it.hasNext()) {
			I_Produit produitTemp = it.next();
			
			message += produitTemp;
		}

		message += "\n" + "Montant total TTC du stock : " + String.format("%.2f", getMontantTotalTTC()) + " €";
		return message;
	}
}
