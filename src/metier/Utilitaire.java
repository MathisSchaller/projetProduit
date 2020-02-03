package metier;

/**
 * Classe repr�sentant divers utilitaires pour l'application
 * 
 * @author Mathis Schaller
 * @author Lo�c Petit
 */
public class Utilitaire 
{
	/**
	 * Arrondi un nombre �virgule � la deuxi�me d�cimal
	 * 
	 * @param nombre Le nombre�� arrondir
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
	 * V�rifie si une cha�ne de caract�res peut �tre parser en Integer
	 * 
	 * @param number Le nombre � parser
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
	 * V�rifie si une cha�ne de caract�res peut �tre parser en Double
	 * 
	 * @param number Le nombre � parser
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
