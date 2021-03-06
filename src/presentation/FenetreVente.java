package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.AchatVenteControlleur;
import exceptions.ProduitException;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	
	private AchatVenteControlleur achatVenteControlleur;

	public FenetreVente(AchatVenteControlleur controlleur) {
		achatVenteControlleur = controlleur;
		
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(achatVenteControlleur.recupererNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {		
		try 
		{
			achatVenteControlleur.vendreQuantite((String) combo.getSelectedItem(), txtQuantite.getText());
			JOptionPane.showMessageDialog(this, "La quantit� a �t� vendue", "Information", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (ProduitException ex) 
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		};
	}

}
