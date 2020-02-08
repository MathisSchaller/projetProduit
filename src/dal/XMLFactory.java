package dal;

/**
 * Fabrique concr�te des classes h�ritant de l'interface I_ProduitDAO et I_CatalogueDAO en XML
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class XMLFactory extends CataloguesProduitsFactory
{	
	@Override
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO() 
	{
		return new AdaptateurProduitDAO_XML();
	}

	@Override
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 */
	public I_CatalogueDAO createCatalogueDAO() 
	{
		return new CatalogueDAO_XML();
	}

}
