package metier;

import java.text.ParseException;

public class Utilitaire {

	public static double arrondirA2decimal(double nombre)
	{
		if(nombre == 0)
		{
			return 0;
		}
		return ((double) Math.round(nombre * 100)) / 100;
	}
	
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
