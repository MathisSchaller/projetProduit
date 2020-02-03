package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * Ajouter un produit
	 * 
	 * @param produit Le produit à ajouter
	 * @return True ou false en fonction de si le produit a été ajouté à la BDD
	 */
	public boolean create(String nom, double prixHT, int quantite)
	{	
		try
		{
			CallableStatement cst = cn.prepareCall("{call nouveauProduit(?, ?, ?)}");
			cst.setString(1, nom);
			cst.setDouble(2, prixHT);
			cst.setInt(3, quantite);

			return cst.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de l'insertion du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer un produit
	 * 
	 * @param nom Le nom du produit
	 * @return True ou false en fonction de si le produit a été supprimé de la BDD
	 */
	public boolean delete(String nom)
	{	
		try
		{
			PreparedStatement pst = cn.prepareStatement("DELETE FROM Produits WHERE nom = ?");
			pst.setString(1, nom);
			
			return pst.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Modifier la quantité d'un produit
	 * 
	 * @param nom Le nom du produit
	 * @param quantite La nouvelle quantité
	 * @return True ou false en fonction de si le produit a été modifié dans la BDD
	 */
	public boolean updateQuantite(String nom, int quantite)
	{	
		try
		{
			PreparedStatement pst = cn.prepareStatement("UPDATE Produits SET quantite = ? WHERE nom = ?");
			pst.setInt(1, quantite);
			pst.setString(2, nom);
			
			return pst.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la modification de la quantité du produit : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Récupèrer tous les produits
	 * 
	 * @return Une liste de produits ou null
	 */
	public List<I_Produit> findAll()
	{
		List<I_Produit> produits = null;
		
		try
		{
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Produits");
			
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

