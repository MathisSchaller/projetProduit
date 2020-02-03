package dal;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

/**
 * Classe adaptant la classe ProduitDAO_XML afin d'utiliser l'interface I_ProduitDAO
 *
 * @author Mathis Schaller
 * @author Loïc
 */
public class AdaptateurProduitDAO_XML implements I_ProduitDAO 
{
	/**
	 * Objet de la classe ProduitDAO_XML
	 */
	private ProduitDAO_XML dao;
	
	/**
	 * Constructeur de la classe
	 */
	public AdaptateurProduitDAO_XML()
	{
		dao = new ProduitDAO_XML();
	}

	@Override
	/**
	 * Ajouter un produit
	 * 
	 * @param produit Le produit à ajouter
	 * @return True ou false en fonction de si le produit a été ajouté à la BDD
	 */
	public boolean create(String nom, double prixHT, int quantite) 
	{
		return dao.creer(new Produit(nom, prixHT, quantite));
	}

	@Override
	/**
	 * Supprimer un produit
	 * 
	 * @param nom Le nom du produit
	 * @return True ou false en fonction de si le produit a été supprimé de la BDD
	 */
	public boolean delete(String nom)
	{
		return dao.supprimer(new Produit(nom, 1, 0));
	}

	@Override
	/**
	 * Modifier la quantité d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantité
	 * @return True ou false en fonction de si le produit a été modifié dans la BDD
	 */
	public boolean updateQuantite(String nom, int quantite) 
	{
		return dao.maj(new Produit(nom, 1, quantite));
	}

	@Override
	/**
	 * Récupèrer tous les produits
	 * 
	 * @return Une liste de produits ou null
	 */
	public List<I_Produit> findAll() 
	{
		return dao.lireTous();
	}

}
