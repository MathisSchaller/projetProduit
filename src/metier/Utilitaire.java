package metier;

public class Utilitaire {

	public static double arrondirA2decimal(double nombre)
	{
		if(nombre == 0)
		{
			return 0;
		}
		return ((double) Math.round(nombre * 100)) / 100;
	}
}
