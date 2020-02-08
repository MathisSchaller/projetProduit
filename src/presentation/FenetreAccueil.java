package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.CatalogueControlleur;
import dal.OracleConnexion;

public class FenetreAccueil extends JFrame implements ActionListener, WindowListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;

	// Controlleur qui permet de gérer les catalogues
	private CatalogueControlleur catalogueControlleur;
	
	public FenetreAccueil() {
		
		catalogueControlleur = new CatalogueControlleur();
		
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);
		
		addWindowListener(this);
		
		actualiserInformations();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// Ajout d'un catalogue
		if (e.getSource() == btAjouter)
		{
			String texteAjout = txtAjouter.getText();
			
			if (!texteAjout.equals(""))
			{
				if(catalogueControlleur.ajouterCatalogue(texteAjout))
				{
					JOptionPane.showMessageDialog(this, "La catalogue a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					actualiserInformations();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Impossible d'ajouter le catalogue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			txtAjouter.setText(null);
		}
		// Suppression d'un catalogue
		if (e.getSource() == btSupprimer)
		{
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null)
			{
				if(catalogueControlleur.supprimerCatalogue(texteSupprime))
				{
					JOptionPane.showMessageDialog(this, "La catalogue a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					actualiserInformations();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Impossible de supprimer le catalogue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		// Selection du catalogue
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = (String)cmbSelectionner.getSelectedItem();
			
			if (texteSelection != null) 
			{				
				// On choisit le catalogue à utiliser
				catalogueControlleur.selectionnerCatalogue(texteSelection);
				// On ouvre la fenêtre principale
				new FenetrePrincipale(catalogueControlleur);
				
				this.dispose();
			}
		}	
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null)
			for (int i=0 ; i<nomsCatalogues.length; i++)
			{
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
	}
	
	private void modifierNbCatalogues(int nb)
	{
		lbNbCatalogues.setText(nb + " catalogues");
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (int i=0 ; i<detailCatalogues.length; i++)
				taDetailCatalogues.append(detailCatalogues[i]+"\n");
		}
	}
	
	private void actualiserInformations()
	{
		String[] tabCatalogueNoms  = catalogueControlleur.recupererNomCatalogues();
		modifierListesCatalogues(tabCatalogueNoms);
		String[] tabCatalogueDetails = catalogueControlleur.recupererDetailsCatalogue();
		modifierDetailCatalogues(tabCatalogueDetails);
		modifierNbCatalogues(tabCatalogueNoms.length);
	}
	
	public void windowClosing(WindowEvent arg0) {		
		// Fermeture de la connexion à la BDD
		OracleConnexion.deconnexion();
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	public static void main(String[] args) {
		new FenetreAccueil();
	}
}
