package metier;

/**
 * @author Mathis Schaller
 * @author Loïc Petit
 * 
 * Classe représentant un produit
 */
public class Produit implements I_Produit {
	/**
	 * Quantité de produits en stock
	 */
	private int quantiteStock;
	/**
	 * Nom du produit
	 */
	private String nom;
	/**
	 * Prix HT du produit
	 */
	private double prixHT;
	/**
	 * Le taux de TVA appliqué au produit
	 */
	private static double tauxTVA = 0.2;
	
	/**
	 * Constructeur de la classe Produit
	 * 
	 * @param nom Nom du produit
	 * @param prixHT Prix hors taxe du produit
	 * @param quantite Quantit� en stock du produit
	 */
	public Produit(String nom, double prixHT, int quantite) {
		this.nom = nom.trim();
		this.prixHT = prixHT;
		this.quantiteStock = quantite;
	}

	/**
	 * Ajouter du stock au produit
	 * 
	 * @param qteAchetee Le quantité à ajouter
	 */
	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee > 0)
		{
			this.quantiteStock += qteAchetee;
			return true;
		}
		
		return false;
	}

	/**
	 * Enlever du stock au produit
	 * 
	 * @param qteAchetee Le quantité à enlever
	 */
	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue > 0 && (this.quantiteStock - qteVendue) >= 0)
		{
			this.quantiteStock -= qteVendue;
			return true;
		}
		
		return false;
	}

	/**
	 * R�cup�re le nom du produit
	 * 
	 * @return Le nom du produit
	 */
	@Override
	public String getNom() {
		return this.nom.replaceAll("	", " ").trim();
	}

	/**
	 * R�cup�re la quantit� de produit en stock
	 * 
	 * @return La quantit� de produit en stock
	 */
	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	/**
	 * R�cup�re le prix unitaire HT du produit
	 * 
	 * @return Le prix HT du produit
	 */
	@Override
	public double getPrixUnitaireHT() {
		return this.prixHT;
	}

	/**
	 * R�cup�re le prix unitaire TTC du produit
	 * 
	 * @return Le prix TTC du produit
	 */
	@Override
	public double getPrixUnitaireTTC() {
		return this.prixHT + (this.prixHT * Produit.tauxTVA);
	}

	/**
	 * R�cup�re le prix TTC total du stock de produit
	 * 
	 * @return Le prix TTC total du stock de produit
	 */
	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock * this.getPrixUnitaireTTC();
	}
	
	/**
	 * Affiche les informations sur le produit
	 * 
	 * @return Les informations sur le produit
	 */
	@Override
	public String toString() {
		String message = getNom() + " - prix HT : " + String.format("%.2f", getPrixUnitaireHT()) + " � - prix TTC : " + String.format("%.2f", getPrixUnitaireTTC()) + " � - quantit� en stock : " + getQuantite() + "\n";
		return message;
	}
}
