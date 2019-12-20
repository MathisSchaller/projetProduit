package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ProduitControlleur;
import exceptions.ProduitException;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	private ProduitControlleur produitControlleur;
	
	public FenetreSuppressionProduit(ProduitControlleur controlleur) {
		
		produitControlleur = controlleur;
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(controlleur.recupererNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try
		{
			produitControlleur.supprimerProduit((String)combo.getSelectedItem());
			combo.removeItem(combo.getSelectedItem());
			JOptionPane.showMessageDialog(this, "Le produit a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(ProduitException ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

}
