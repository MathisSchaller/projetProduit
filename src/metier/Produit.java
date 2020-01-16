package metier;

import java.util.List;

import dal.OracleConnexion;
import dal.ProduitDAOFactory;
import dal.ProduitDAORelationnelle;
import dal.I_ProduitDAO;

/**
 * Classe représentant un produit
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class Produit implements I_Produit 
{
	/**
	 * Quantité de produits en stock
	 */
	private int quantiteStock;
	
	/**
	 * Nom du produit
	 */
	private String nom;
	
	/**
	 * Prix HT du produit
	 */
	private double prixHT;
	
	/**
	 * Le taux de TVA appliqué au produit
	 */
	private static double tauxTVA = 0.2;
	
	/**
	 * Le DAO de la classe Produit créé par la fabrique ProduitDAOFactory
	 */
	private static I_ProduitDAO dao = ProduitDAOFactory.getInstance().createProduitDAORelationnelle();
	
	/**
	 * Constructeur de la classe Produit
	 * 
	 * @param nom Nom du produit
	 * @param prixHT Prix hors taxe du produit
	 * @param quantite Quantité en stock du produit
	 */
	public Produit(String nom, double prixHT, int quantite) 
	{		
		this.nom = nom.trim();
		this.prixHT = prixHT;
		this.quantiteStock = quantite;
	}

	/**
	 * Ajouter du stock au produit
	 * 
	 * @param qteAchetee Le quantité à ajouter
	 */
	@Override
	public boolean ajouter(int qteAchetee) 
	{
		if(qteAchetee > 0)
		{
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}

	/**
	 * Enlever du stock au produit
	 * 
	 * @param qteAchetee Le quantité à enlever
	 */
	@Override
	public boolean enlever(int qteVendue) 
	{
		if(qteVendue > 0 && (this.quantiteStock - qteVendue) >= 0)
		{
			this.quantiteStock -= qteVendue;
			return true;
		}
		return false;
	}

	/**
	 * Récupère le nom du produit
	 * 
	 * @return Le nom du produit
	 */
	@Override
	public String getNom() 
	{
		return this.nom.replaceAll("	", " ").trim();
	}

	/**
	 * Récupère la quantité de produit en stock
	 * 
	 * @return La quantité de produit en stock
	 */
	@Override
	public int getQuantite() 
	{
		return this.quantiteStock;
	}

	/**
	 * Récupère le prix unitaire HT du produit
	 * 
	 * @return Le prix HT du produit
	 */
	@Override
	public double getPrixUnitaireHT() 
	{
		return this.prixHT;
	}

	/**
	 * Récupère le prix unitaire TTC du produit
	 * 
	 * @return Le prix TTC du produit
	 */
	@Override
	public double getPrixUnitaireTTC() 
	{
		return this.prixHT + (this.prixHT * Produit.tauxTVA);
	}

	/**
	 * Récupèree le prix TTC total du stock de produit
	 * 
	 * @return Le prix TTC total du stock de produit
	 */
	@Override
	public double getPrixStockTTC() 
	{
		return this.quantiteStock * this.getPrixUnitaireTTC();
	}
	
	/**
	 * Affiche les informations sur le produit
	 * 
	 * @return Les informations sur le produit
	 */
	@Override
	public String toString() 
	{
		String message = getNom() + " - prix HT : " + String.format("%.2f", getPrixUnitaireHT()) + " € - prix TTC : " + String.format("%.2f", getPrixUnitaireTTC()) + " € - quantité en stock : " + getQuantite() + "\n";
		return message;
	}
	
	/**
	 * Sauvegarde le produit dans la BDD
	 * 
	 * @return True ou false en fonction de si le produit a été sauvegardé
	 */
	public boolean save()
	{
		return dao.create(this.nom, this.prixHT, this.quantiteStock);
	}
	
	/**
	 * Supprime le produit dans la BDD
	 * 
	 * @return True ou false en fonction de si le produit a été supprimé
	 */
	public boolean delete()
	{
		return dao.delete(this.nom);
	}
	
	/**
	 * Modifie la quantité du produit dans la BDD
	 * 
	 * @return True ou false en fonction de si la quantité du produit a été modifié
	 */
	public boolean updateQuantite()
	{
		return dao.updateQuantite(this.nom, this.quantiteStock);
	}
	
	/**
	 * Récupère tous les produits dans la BDD
	 * 
	 * @return La liste des produits ou null
	 */
	public static List<I_Produit> getAll()
	{
		return dao.findAll();
	}
}