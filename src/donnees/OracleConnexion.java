package donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Produit;

public class OracleConnexion {

	private Statement st;
	private ResultSet rs;
	private Connection cn;

	public OracleConnexion() {
		st = null;
		rs = null;
		cn = null;

		try
		{
			// Enregistrement du driver JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@gloin:1521:iut";
			String login = "petitl";
			String mdp = "1109006350K";

			// Connexion à la base de donnée
			cn = DriverManager.getConnection(url, login, mdp);

			// Récupération du statement associé (Curseur naviguable mais pas modifiable)
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Récupération de toutes les tuples de la table Personnes
			rs = st.executeQuery("SELECT * FROM Produit ORDER BY nom DESC NULLS LAST");
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

	public Produit premier() {
		int num = 0, qte = 0;
		double prixHT = 0;
		String nom = "";

		try
		{
			if(rs.first())
			{
				num = rs.getInt(1);
				nom = rs.getString(2);
				prixHT = rs.getDouble(3);
				qte = rs.getInt(4);
				// Si l'age est null alors on l'initialise à -1;
				if(rs.wasNull())
				{
					prixHT = -1;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return new Produit(nom,prixHT,qte);
	}

	public Produit dernier() {
		int num = 0, qte = 0;
		double prixHT = 0;
		String nom = "";

		try
		{
			if(rs.last())
			{
				num = rs.getInt(1);
				nom = rs.getString(2);
				prixHT = rs.getDouble(3);
				qte = rs.getInt(4);
				// Si l'age est null alors on l'initialise à -1;
				if(rs.wasNull())
				{
					prixHT = -1;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return new Produit(nom,prixHT,qte);
	}

	public Produit suivant() {
		int num = 0, qte = 0;
		double prixHT = 0;
		String nom = "";

		try
		{
			if(rs.next())
			{
				num = rs.getInt(1);
				nom = rs.getString(2);
				prixHT = rs.getDouble(3);
				qte = rs.getInt(4);
				// Si l'age est null alors on l'initialise à -1;
				if(rs.wasNull())
				{
					prixHT = -1;
				}
			}
			else
			{
				if(rs.previous())
				{
					num = rs.getInt(1);
					nom = rs.getString(2);
					prixHT = rs.getDouble(3);
					qte = rs.getInt(4);
					// Si l'age est null alors on l'initialise à -1;
					if(rs.wasNull())
					{
						prixHT = -1;
					}
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return new Produit(nom,prixHT,qte);
	}

	public Produit precedent() {
		int num = 0, qte = 0;
		double prixHT = 0;
		String nom = "";

		try
		{
			if(rs.previous())
			{
				num = rs.getInt(1);
				nom = rs.getString(2);
				prixHT = rs.getDouble(3);
				qte = rs.getInt(4);
				// Si l'age est null alors on l'initialise à -1;
				if(rs.wasNull())
				{
					prixHT = -1;
				}
			}
			else
			{
				if(rs.next())
				{
					num = rs.getInt(1);
					nom = rs.getString(2);
					prixHT = rs.getDouble(3);
					qte = rs.getInt(4);
					// Si l'age est null alors on l'initialise à -1;
					if(rs.wasNull())
					{
						prixHT = -1;
					}
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return new Produit(nom,prixHT,qte);
	}

	public void Deconnexion() {
		if(cn != null)
		{
			try
			{
				cn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}