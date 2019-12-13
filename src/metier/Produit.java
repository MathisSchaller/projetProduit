/**
 * 
 */
package metier;

/**
 * @author petitl
 *
 */
public class Produit implements I_Produit {
	
	private int quantiteStock;
	private String nom;
	private double prixHT;
	private static double tauxTVA = 0.2;
	
	public Produit(String nom, double prixHT, int quantite)
	{
		this.nom = nom;
		this.prixHT = prixHT;
		this.quantiteStock = quantite;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNom() {
		
		return this.nom;
	}

	@Override
	public int getQuantite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPrixUnitaireHT() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPrixUnitaireTTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPrixStockTTC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
