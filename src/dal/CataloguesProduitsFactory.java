package dal;

/**
 * Fabrique abstraite des classes h�ritant de l'interface I_ProduitDAO et I_CatalogueDAO
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
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
	 * R�cup�re une instance de la classe XMLFactory ou RelationnelleFactory
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
	 * Cr�er une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 */
	public abstract I_ProduitDAO createProduitDAO();
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 */
	public abstract I_CatalogueDAO createCatalogueDAO();
}
