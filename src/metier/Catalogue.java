package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import dal.OracleConnexion;

import java.util.Iterator;

/**
 * Classe représentant un catalogue de produit
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class Catalogue implements I_Catalogue 
{
	/**
	 * Liste des produits
	 */
	private List<I_Produit> lesProduits;
	
	/**
	 * Constructeur de la classe CatalogueProduit
	 */
	public Catalogue() 
	{
		getProduits();
	}
	
	/**
	 * Récupération des produits de la BDD
	 * 
	 * @return Les produits stockés dans la BDD
	 */
	private void getProduits() 
	{
		lesProduits = Produit.getAll();
	}


	/**
	 * Ajouter un produit
	 * 
	 * @param produit Le produit à ajouter
	 * @return True ou false en fonction de si le produit a été ajouté
	 */
	@Override
	public boolean addProduit(I_Produit produit) 
	{
		if(produit != null)
		{
			if(!existe(produit.getNom()) && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) 
			{
				// On créé un produit temporaire
				Produit produitBDD = new Produit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite());
				
				lesProduits.add(produitBDD);
				
				// On sauvegarde dans la BDD
				produitBDD.save();
				
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
	 * @param qte Quantité du produit
	 * @return True ou false en fonction de si le produit a été ajouté
	 */
	@Override
	public boolean addProduit(String nom, double prix, int qte) 
	{
		if(!existe(nom.trim()) && prix > 0 && qte >= 0) 
		{
			// On créé un produit temporaire
			Produit produitBDD = new Produit(nom, prix, qte);
			
			lesProduits.add(produitBDD);
			
			// On sauvegarde dans la BDD
			produitBDD.save();
			
			return true;
		}
		return false;
	}

	/**
	 * Ajouter une liste de produits
	 * 
	 * @param l Liste de produits
	 * @return Le nombre de produit ajoutés
	 */
	@Override
	public int addProduits(List<I_Produit> l) 
	{
		int produitAjoute = 0;
		
		if(l != null)
		{
			Iterator<I_Produit> it = l.iterator();
			
			while(it.hasNext()) 
			{
				I_Produit produitTemp = it.next();
				
				if(!existe(produitTemp.getNom()) && produitTemp.getPrixUnitaireHT() > 0 && produitTemp.getQuantite() >= 0) 
				{
					// On créé un produit temporaire
					Produit produitBDD = new Produit(produitTemp.getNom(), produitTemp.getPrixUnitaireHT(), produitTemp.getQuantite());
					
					lesProduits.add(produitBDD);
					
					// On sauvegarde dans la BDD
					produitBDD.save();
					
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
	 * @return True ou false en fonction de si le produit a été enlevés
	 */
	@Override
	public boolean removeProduit(String nom) 
	{
		int indexProduit = this.trouverIndex(nom);
		
		if(indexProduit > -1)
		{
			// On récupère le produit
			Produit produitTemp = (Produit) lesProduits.get(indexProduit);

			lesProduits.remove(indexProduit);
			
			// On supprime le produit la BDD
			produitTemp.delete();
			
			return true;
		}
		return false;
	}

	/**
	 * Acheter du stock du produit
	 * 
	 * @param nomProduit Nom du produit
	 * @param qteAchetee Quantité du produit à ajouter
	 * @return True ou false en fonction de si la quantité du produit a été modifié
	 */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) 
	{
		if(qteAchetee > 0)
		{
			int indexProduit = this.trouverIndex(nomProduit);
			
			if(indexProduit > -1)
			{
				if(lesProduits.get(indexProduit).ajouter(qteAchetee))
				{
					// On récupère le produit
					Produit produitTemp = (Produit) lesProduits.get(indexProduit);
					
					// On modifie la quantité dans la BDD
					produitTemp.updateQuantite();
					
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
	 * @param qteVendue Quantité du produit à enlever
	 * @return True ou false en fonction de si la quantité du produit a été modifié
	 */
	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) 
	{
		if(qteVendue > 0)
		{
			int indexProduit = this.trouverIndex(nomProduit);
			
			if(indexProduit > -1)
			{
				if(lesProduits.get(indexProduit).enlever(qteVendue))
				{
					// On récupère le produit
					Produit produitTemp = (Produit) lesProduits.get(indexProduit);
					
					// On modifie la quantité dans la BDD
					produitTemp.updateQuantite();
					
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Récupère les noms de tous les produits
	 * 
	 * @return Tableau contenant le nom des produits
	 */
	@Override
	public String[] getNomProduits() 
	{
		// Création d'un tableau de String qui prend la taille de la liste des produits
		String[] nomProduits = new String[lesProduits.size()];
		// On parcours la liste avec un iterateur
		Iterator<I_Produit> it = lesProduits.iterator();
		int i;
		
		for(i = 0; it.hasNext(); i++) 
		{
			I_Produit produitTemp = it.next();
			
			nomProduits[i] = produitTemp.getNom();
		}
		Arrays.sort(nomProduits);
		return nomProduits;
	}
	
	/**
	 * Vérifie si le nom du produit existe dans la liste des produits
	 * 
	 * @param nomProduit Nom du produit
	 * @return True ou false en fonction l'existence du produit
	 */
	private boolean existe(String nomProduit) 
	{
		String[] nomsProduits = this.getNomProduits();
		
		for(String nom : nomsProduits) 
		{
			if(nom.equals(nomProduit)) 
			{
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
	 * Récupère le montant TTC total de tous les produits (en prenant en compte le stock)
	 * 
	 * @return Le montant total TTC
	 */
	@Override
	public double getMontantTotalTTC() 
	{
		Iterator<I_Produit> it = lesProduits.iterator();
		double montantTotalTTC = 0.0;
		
		while(it.hasNext()) 
		{
			I_Produit produitTemp = it.next();
			
			montantTotalTTC += produitTemp.getPrixStockTTC();
		}
		return Utilitaire.arrondirA2decimal(montantTotalTTC);
	}

	@Override
	/**
	 * Vide la liste des produits
	 */
	public void clear() 
	{
		lesProduits.clear();
		
		// Ces instructions permettent de supprimer les données de la table Produits
		Connection cn = OracleConnexion.getInstance();
		
		try
		{
			PreparedStatement pst = cn.prepareStatement("DELETE FROM Produits");
			
			pst.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur suppression : " + e.getMessage());
		}
	}
	
	@Override
	/**
	 * Affiche le catalogue
	 * 
	 * @return Les informations sur les produits dans le catalogue
	 */
	public String toString() 
	{
		String message = "";
		
		Iterator<I_Produit> it = lesProduits.iterator();
		
		while(it.hasNext()) 
		{
			I_Produit produitTemp = it.next();
			
			message += produitTemp;
		}
		message += "\n" + "Montant total TTC du stock : " + String.format("%.2f", getMontantTotalTTC()) + " €";
		return message;
	}
}
