package dal;

/**
 * Fabrique abstraite des classes héritant de l'interface I_ProduitDAO et I_CatalogueDAO
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public abstract class CataloguesProduitsFactory 
{
	/**
	 * Instance de CataloguesProduitsFactory
	 */
	private static CataloguesProduitsFactory instance;
	
	/**
	 * Constructeur de la classe
	 */
	protected CataloguesProduitsFactory() {}
	
	/**
	 * Récupére une instance de la classe XMLFactory ou RelationnelleFactory
	 * 
	 * @return Une instance de la classe XMLFactory ou RelationnelleFactory
	 */
	public synchronized static CataloguesProduitsFactory getInstance()
	{
		if(instance == null)
		{
//			instance = new XMLFactory();
			instance = new RelationnelleFactory();
		}
		return instance;
	}
	
	/**
	 * Créer une instance d'une classe héritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_ProduitDAO
	 */
	public abstract I_ProduitDAO createProduitDAO();
	/**
	 * Créer une instance d'une classe héritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_CatalogueDAO
	 */
	public abstract I_CatalogueDAO createCatalogueDAO();
}
