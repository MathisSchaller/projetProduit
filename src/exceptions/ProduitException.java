package exceptions;

/**
 * Exception concernant les produits
 * 
 * @author Mathis Schaller
 * @author Lo�c
 */
public class ProduitException extends Exception 
{
	/**
	 * Constructeur de la classe
	 * 
	 * @param message Le message � afficher
	 */
	public ProduitException(String message) 
	{
		super(message);
	}
}
