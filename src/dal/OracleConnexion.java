package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe repr�sentant la connexion � une base de donn�e Oracle
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class OracleConnexion 
{
	/**
	 * Variable de connexion � la BDD
	 */
	private static Connection cn;

	/**
	 * Constructeur
	 */
	protected OracleConnexion() {}
	
	/**
	 * Renvoyer la connexion � la BDD
	 * 
	 * @return La variable de connexion � la BDD
	 */
	public synchronized static Connection getInstance() 
	{
		if(cn == null) 
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");

				String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
				//String url = "jdbc:oracle:thin:@gloin:1521:iut";
				String login = ""; // A MODIFIER
				String mdp = ""; // A MODIFIER
				
				// Connexion � la base de donn�es
				cn = DriverManager.getConnection(url, login, mdp);
				System.out.println("Connexion � la BDD");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return cn;
	}
	
	/**
	 * Fermer la connexion � la BDD
	 */
	public static void deconnexion() 
	{
		if(cn != null)
		{
			try
			{
				cn.close();
				System.out.println("D�connexion de la BDD");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}