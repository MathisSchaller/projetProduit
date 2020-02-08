package dal;

import java.util.List;
import metier.I_Catalogue;

/**
 * Interface de CatalogueDAO
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit 
 */
public interface I_CatalogueDAO 
{	
	/**
	 * Ajoute un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a �t� ajout� � la BDD
	 */
	public abstract boolean create(String nom);
	
	/**
	 * Supprime un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a �t� supprim� de la BDD
	 */
	public abstract boolean delete(String nom);
	
	/**
	 * R�cup�re tous les catalogues
	 * 
	 * @return La liste de catalogues
	 */
	public abstract List<I_Catalogue> findAll();
	
	/**
	 * R�cup�re le nombre de produits d'un catalogue
	 * 
	 * @param nomCatalogue Le nom du catalogue
	 * @return Le nombre de produits
	 */
	public abstract int getNombreProduits(String nomCatalogue);
}
