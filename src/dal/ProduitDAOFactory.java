package dal;

/**
 * Fabrique concrète des classes héritant de l'interface I_ProduitDAO
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
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
	 * Créer une instance d'une classe héritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO()
	{
		return new ProduitDAO_Relationnelle(OracleConnexion.getInstance());
	}
	
	/**
	 * Récupérer une instance de la classe ProduitDAOFactory
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
