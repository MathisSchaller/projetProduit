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
	 * Ajoute un produit
	 * 
	 * @param nom Nom du produit
	 * @param prixHT Prix du produit
	 * @param quantite Quantit� du produit
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a �t� ajout� � la BDD
	 */
	public abstract boolean create(String nom, double prixHT, int quantite, String nomCatalogue);
	
	/**
	 * Supprime un produit
	 * 
	 * @param nom Le nom du produit
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a �t� supprim� de la BDD
	 */
	public abstract boolean delete(String nom, String nomCatalogue);
	
	/**
	 * Modifie la quantit� d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantit�
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a �t� modifi� dans la BDD
	 */
	public abstract boolean updateQuantite(String nom, int quantite, String nomCatalogue);
	
	/**
	 * R�cup�re tous les produits d'un catalogue
	 * 
	 * @param nomCatalogue Le nom du catalogue
	 * @return la liste de produits
	 */
	public abstract List<I_Produit> findAll(String nomCatalogue);
}
