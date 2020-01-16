package dal;

import java.util.List;

import metier.I_Produit;

/**
 * Interface de ProduitDAO
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit 
 */
public interface I_ProduitDAO 
{	
	/**
	 * Ajouter un produit
	 * 
	 * @param produit Le produit � ajouter
	 * @return True ou false en fonction de si le produit a �t� ajout� � la BDD
	 */
	public abstract boolean create(String nom, double prixHT, int quantite);
	
	/**
	 * Supprimer un produit
	 * 
	 * @param nom Le nom du produit
	 * @return True ou false en fonction de si le produit a �t� supprim� de la BDD
	 */
	public abstract boolean delete(String nom);
	
	/**
	 * Modifier la quantit� d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantit�
	 * @return True ou false en fonction de si le produit a �t� modifi� dans la BDD
	 */
	public abstract boolean updateQuantite(String nom, int quantite);
	
	/**
	 * R�cup�rer tous les produits
	 * 
	 * @return Une liste de produits ou null
	 */
	public abstract List<I_Produit> findAll();
}
