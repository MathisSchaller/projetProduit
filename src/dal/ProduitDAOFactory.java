package dal;

/**
 * Fabrique concr�te des classes h�ritant de l'interface I_ProduitDAO
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class ProduitDAOFactory {

	/**
	 * Instance de ProduitDAOFactory
	 */
	private static ProduitDAOFactory instance;
	
	/**
	 * Constructeur de la classe
	 */
	protected ProduitDAOFactory() {}
	
	/**
	 * Cr�er une instance de la classe ProduitDAORelationnelle
	 * 
	 * @return Une instance de la classe ProduitDAORelationnelle
	 */
	public ProduitDAORelationnelle createProduitDAORelationnelle()
	{
		return new ProduitDAORelationnelle(OracleConnexion.getInstance());
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
