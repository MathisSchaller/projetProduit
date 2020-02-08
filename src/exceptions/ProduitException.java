package exceptions;

/**
 * Exception concernant les produits
 * 
 * @author Mathis Schaller
 * @author Loïc
 */
public class ProduitException extends Exception 
{
	/**
	 * Constructeur de la classe
	 * 
	 * @param message Le message à afficher
	 */
	public ProduitException(String message) 
	{
		super(message);
	}
}
