package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ProduitControlleur;
import metier.Catalogue;
import metier.Utilitaire;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	
	private ProduitControlleur produitControlleur;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(Catalogue catalogueProduit) {	

		produitControlleur = new ProduitControlleur(catalogueProduit);
		
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("QuantitÃ© en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		int creationProduit = produitControlleur.ajouterProduit(txtNom.getText(), txtPrixHT.getText(), txtQte.getText());
		
		switch(creationProduit)
		{
			case ProduitControlleur.PRODUIT_CREE:
				System.out.println("Produit créé");
				break;
			case ProduitControlleur.PRODUIT_ERREUR_EXISTANT:
				System.out.println("Produit existe déjà");
				break;
			case ProduitControlleur.PRODUIT_ERREUR_NOM:
				System.out.println("Erreur au niveau du nom");
				break;
			case ProduitControlleur.PRODUIT_ERREUR_PRIX:
				System.out.println("Erreur au niveau du prix");
				break;
			case ProduitControlleur.PRODUIT_ERREUR_QUANTITE:
				System.out.println("Erreur au niveau de la quantité");
				break;
		}
		
	}

}