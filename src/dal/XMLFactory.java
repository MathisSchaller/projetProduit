package dal;

/**
 * Fabrique concrète des classes héritant de l'interface I_ProduitDAO et I_CatalogueDAO en XML
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class XMLFactory extends CataloguesProduitsFactory
{	
	@Override
	/**
	 * Créer une instance d'une classe héritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO() 
	{
		return new AdaptateurProduitDAO_XML();
	}

	@Override
	/**
	 * Créer une instance d'une classe héritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_CatalogueDAO
	 */
	public I_CatalogueDAO createCatalogueDAO() 
	{
		return new CatalogueDAO_XML();
	}

}
