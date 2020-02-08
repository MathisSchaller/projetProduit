package dal;

import java.sql.Connection;

/**
 * Fabrique concrète des classes héritant de l'interface I_ProduitDAO et I_CatalogueDAO en relationnelle
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class RelationnelleFactory extends CataloguesProduitsFactory
{
	/**
	 * Variable de connexion à la BDD
	 */
	private Connection cn = OracleConnexion.getInstance();
	
	@Override
	/**
	 * Créer une instance d'une classe héritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO() 
	{
		return new ProduitDAO_Relationnelle(cn);
	}

	@Override
	/**
	 * Créer une instance d'une classe héritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe héritant de l'interface I_CatalogueDAO
	 */
	public I_CatalogueDAO createCatalogueDAO() 
	{
		return new CatalogueDAO_Relationnelle(cn);
	}

}
