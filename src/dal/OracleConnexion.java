package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mathis Schaller
 * @author Lo�c Petit
 * 
 * Classe repr�sentant la connexion � une base de donn�e Oracle
 */
public class OracleConnexion {
	/**
	 * Variable de connexion � la BDD
	 */
	private static Connection cn;

	/**
	 * Renvoyer la connexion � la BDD
	 * 
	 * @return La variable de connexion � la BDD
	 */
	public static Connection getInstance() {
		
		if(cn == null) {
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");

				String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
				String login = "";
				String mdp = "";
				
				// Connexion � la base de donn�es
				cn = DriverManager.getConnection(url, login, mdp);
				System.out.println("Cr�ation");
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
	public static void Deconnexion() {
		if(cn != null)
		{
			try
			{
				cn.close();
				System.out.println("D�connexion");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}