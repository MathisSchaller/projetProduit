package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe représentant la connexion à une base de donnée Oracle
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class OracleConnexion {
	/**
	 * Variable de connexion à la BDD
	 */
	private static Connection cn;

	/**
	 * Renvoyer la connexion à la BDD
	 * 
	 * @return La variable de connexion à la BDD
	 */
	public static Connection getInstance() {
		
		if(cn == null) {
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");

				//String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
				String url = "jdbc:oracle:thin:@gloin:1521:iut";
				String login = "petitl";
				String mdp = "1109006350K";
				
				// Connexion à  la base de données
				cn = DriverManager.getConnection(url, login, mdp);
				System.out.println("Création");
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
	 * Fermer la connexion à la BDD
	 */
	public static void Deconnexion() {
		if(cn != null)
		{
			try
			{
				cn.close();
				System.out.println("Déconnexion");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}