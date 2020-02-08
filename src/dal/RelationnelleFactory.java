package dal;

import java.sql.Connection;

/**
 * Fabrique concr�te des classes h�ritant de l'interface I_ProduitDAO et I_CatalogueDAO en relationnelle
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class RelationnelleFactory extends CataloguesProduitsFactory
{
	/**
	 * Variable de connexion � la BDD
	 */
	private Connection cn = OracleConnexion.getInstance();
	
	@Override
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_ProduitDAO
	 */
	public I_ProduitDAO createProduitDAO() 
	{
		return new ProduitDAO_Relationnelle(cn);
	}

	@Override
	/**
	 * Cr�er une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 * 
	 * @return Une instance d'une classe h�ritant de l'interface I_CatalogueDAO
	 */
	public I_CatalogueDAO createCatalogueDAO() 
	{
		return new CatalogueDAO_Relationnelle(cn);
	}

}
