package metier;

/**
 * Classe représentant divers utilitaires pour l'application
 * 
 * @author Mathis Schaller
 * @author Loïc Petit
 */
public class Utilitaire 
{
	/**
	 * Arrondi un nombre à virgule à la deuxième décimal
	 * 
	 * @param nombre Le nombre à arrondir
	 * @return Le nombre arrondi
	 */
	public static double arrondirA2decimal(double nombre)
	{
		if(nombre == 0)
		{
			return 0;
		}
		return ((double) Math.round(nombre * 100)) / 100;
	}
	
	/**
	 * Vérifie si une chaîne de caractères peut être parser en Integer
	 * 
	 * @param number Le nombre à parser
	 * @return True ou false
	 */
	public static boolean isInteger(String number)
	{
	    try
	    {
	        Integer.parseInt(number);
	        return true;
	    }
	    catch(NumberFormatException e)
	    {
	    	return false;
	    }
	}
	
	/**
	 * Vérifie si une chaîne de caractères peut être parser en Double
	 * 
	 * @param number Le nombre à parser
	 * @return True ou false
	 */
	public static boolean isDouble(String number)
	{
	    try
	    {
	        Double.parseDouble(number);
	        return true;
	    }
	    catch(NumberFormatException e)
	    {
	    	return false;
	    }
	}
}
