package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.AchatVenteControlleur;
import application.ProduitControlleur;
import exceptions.ProduitException;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	private AchatVenteControlleur achatVenteControlleur;

	public FenetreAchat(AchatVenteControlleur controlleur) {
		achatVenteControlleur = controlleur;

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(achatVenteControlleur.recupererNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try 
		{
			achatVenteControlleur.acheterQuantite((String) combo.getSelectedItem(), txtQuantite.getText());
			JOptionPane.showMessageDialog(this, "La quantité a été achetée", "Information", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (ProduitException ex) 
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		};
	}

}
