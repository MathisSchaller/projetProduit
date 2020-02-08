package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

/**
 * Classe CRUD de la classe Produit
 * 
 * @author Mathis Schaller
 * @author Loïc Petit 
 */
public class ProduitDAO_Relationnelle implements I_ProduitDAO
{
	/**
	 * Variable de connexion à la BDD
	 */
	private Connection cn;
	
	/**
	 * Constructeur de la classe ProduitDAO_Relationnelle
	 * 
	 * @param cn La connexion à la BDD
	 */
	public ProduitDAO_Relationnelle(Connection cn)
	{
		this.cn = cn;
	}
	
	/**
	 * Ajoute un produit
	 * 
	 * @param nom Nom du produit
	 * @param prixHT Prix du produit
	 * @param quantite Quantité du produit
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a été ajouté à la BDD
	 */
	public boolean create(String nom, double prixHT, int quantite, String nomCatalogue)
	{	
		try
		{
			CallableStatement cst = cn.prepareCall("{call nouveauProduit(?, ?, ?, ?)}");
			cst.setString(1, nom);
			cst.setDouble(2, prixHT);
			cst.setInt(3, quantite);
			cst.setString(4, nomCatalogue);
			
			// executeUpdate retourne le nombre de tuples ajoutées
			if(cst.executeUpdate() > 0)
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de l'insertion du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprime un produit
	 * 
	 * @param nom Le nom du produit
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a été supprimé de la BDD
	 */
	public boolean delete(String nom, String nomCatalogue)
	{	
		try
		{
			PreparedStatement pst = cn.prepareStatement("DELETE FROM Produits WHERE nom = ? AND idCatalogue = findIdCatalogue(?)");
			pst.setString(1, nom);
			pst.setString(2, nomCatalogue);
			
			if(pst.executeUpdate() > 0)
			{
				return true;
			}

			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Modifie la quantité d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantité
	 * @param nomCatalogue Nom du catalogue
	 * @return True ou false en fonction de si le produit a été modifié dans la BDD
	 */
	public boolean updateQuantite(String nom, int quantite, String nomCatalogue)
	{	
		try
		{
			PreparedStatement pst = cn.prepareStatement("UPDATE Produits SET quantite = ? WHERE nom = ? AND idCatalogue = findIdCatalogue(?)");
			pst.setInt(1, quantite);
			pst.setString(2, nom);
			pst.setString(3, nomCatalogue);
			
			if(pst.executeUpdate() > 0)
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la modification de la quantité du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Récupère tous les produits d'un catalogue
	 * 
	 * @param nomCatalogue Le nom du catalogue
	 * @return La liste de produits
	 */
	public List<I_Produit> findAll(String nomCatalogue)
	{
		List<I_Produit> produits = null;
		
		try
		{
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits WHERE idCatalogue = findIdCatalogue(?)");
			pst.setString(1, nomCatalogue);
			
			ResultSet rs = pst.executeQuery();
						
			produits = new ArrayList<I_Produit>();
			
			while(rs.next())
			{
				produits.add(new Produit(rs.getString(2), rs.getDouble(3), rs.getInt(4)));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
		}
		return produits;
	}
}

