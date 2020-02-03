package dal;

/**
 * Fabrique concr�te des classes h�ritant de l'interface I_ProduitDAO
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class ProduitDAOFactory 
{
	/**
	 * Instance de ProduitDAOFactory
	 */
	private static ProduitDAOFactory instance;
	
	/**
	 * Constructeur de la classe
	 */
	protected ProduitDAOFactory() {}
	
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO()
	{
		return new ProduitDAO_Relationnelle(OracleConnexion.getInstance());
	}
	
	/**
	 * R�cup�rer une instance de la classe ProduitDAOFactory
	 * 
	 * @return Une instance de la classe ProduitDAOFactory
	 */
	public static synchronized ProduitDAOFactory getInstance()
	{
		if (instance == null)
		{
			instance = new ProduitDAOFactory();
		}
		return instance;
	}
}
