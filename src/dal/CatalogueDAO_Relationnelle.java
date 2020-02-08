package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import metier.Catalogue;
import metier.I_Catalogue;

/**
 * Classe CRUD de la classe Catalogue
 * 
 * @author Mathis Schaller
 * @author Loïc Petit 
 */
public class CatalogueDAO_Relationnelle implements I_CatalogueDAO
{
	/**
	 * Variable de connexion à la BDD
	 */
	private Connection cn;
	
	/**
	 * Constructeur de la classe CatalogueDAO_Relationnelle
	 * 
	 * @param cn La connexion à la BDD
	 */
	public CatalogueDAO_Relationnelle(Connection cn)
	{
		this.cn = cn;
	}
	
	/**
	 * Ajoute un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a été ajouté à la BDD
	 */
	public boolean create(String nom)
	{	
		try
		{
			CallableStatement cst = cn.prepareCall("{call nouveauCatalogue(?)}");
			cst.setString(1, nom);
			
			// executeUpdate retourne le nombre de tuples ajoutées
			if(cst.executeUpdate() > 0)
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de l'insertion du catalogue : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprime un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a été supprimé de la BDD
	 */
	public boolean delete(String nom)
	{	
		try
		{
			PreparedStatement pst = cn.prepareStatement("DELETE FROM Catalogues WHERE nom = ?");
			pst.setString(1, nom);
			
			if(pst.executeUpdate() > 0)
			{
				return true;
			}

			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la suppression du catalogue : " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Récupère tous les catalogues
	 * 
	 * @return La liste de catalogues
	 */
	public List<I_Catalogue> findAll()
	{
		List<I_Catalogue> catalogues = new ArrayList<I_Catalogue>();
		
		try
		{
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Catalogues");
						
			while(rs.next())
			{
				catalogues.add(new Catalogue(rs.getString(2)));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la récupération des catalogues : " + e.getMessage());
		}
		return catalogues;
	}
	
	/**
	 * Récupère le nombre de produits d'un catalogue
	 * 
	 * @return Le nombre de produits
	 */
	public int getNombreProduits(String nomCatalogue)
	{		
		try
		{
			PreparedStatement pst = cn.prepareStatement("SELECT count(*) FROM Produits WHERE idCatalogue = findIdCatalogue(?)");
			pst.setString(1, nomCatalogue);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			return rs.getInt(1);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur : " + e.getMessage());
			return 0;
		}
	}
}

