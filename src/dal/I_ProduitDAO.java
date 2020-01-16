package dal;

import java.util.List;

import metier.I_Produit;

/**
 * Interface de ProduitDAO
 * 
 * @author Mathis Schaller
 * @author Loïc Petit 
 */
public interface I_ProduitDAO 
{	
	/**
	 * Ajouter un produit
	 * 
	 * @param produit Le produit à ajouter
	 * @return True ou false en fonction de si le produit a été ajouté à la BDD
	 */
	public abstract boolean create(String nom, double prixHT, int quantite);
	
	/**
	 * Supprimer un produit
	 * 
	 * @param nom Le nom du produit
	 * @return True ou false en fonction de si le produit a été supprimé de la BDD
	 */
	public abstract boolean delete(String nom);
	
	/**
	 * Modifier la quantité d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantité
	 * @return True ou false en fonction de si le produit a été modifié dans la BDD
	 */
	public abstract boolean updateQuantite(String nom, int quantite);
	
	/**
	 * Récupèrer tous les produits
	 * 
	 * @return Une liste de produits ou null
	 */
	public abstract List<I_Produit> findAll();
}
