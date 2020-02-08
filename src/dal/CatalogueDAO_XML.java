package dal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import metier.Catalogue;
import metier.I_Catalogue;

/**
 * Classe CRUD de la classe Catalogue
 * 
 * @author Mathis Schaller
 * @author Loïc Petit 
 */
public class CatalogueDAO_XML implements I_CatalogueDAO
{
	/**
	 * Chemin vers le fichier xml contenant les catalogues
	 */
	private String uriCatalogues = "D:/Catalogues.xml";
	
	/**
	 * Chemin vers le fichier xml contenant les produits
	 */
	private String uriProduits = "D:/Produits.xml";
	
	/**
	 * Attribut permettant d'accéder au fichier xml
	 */
	private Document doc;
	
	/**
	 * Constructeur de la classe
	 */
	public CatalogueDAO_XML() 
	{
		SAXBuilder sdoc = new SAXBuilder();
		
		try 
		{
			doc = sdoc.build(uriCatalogues);
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur de construction de l'arbre JDOM");
		}
	}
	
	/**
	 * Ajoute un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a été ajouté à la BDD
	 */
	public boolean create(String nom)
	{	
		if(chercheCatalogue(nom) == null)
		{
			try 
			{
				Element root = doc.getRootElement();
				Element prod = new Element("catalogue");
				prod.setAttribute("nom", nom);
				root.addContent(prod);
				return sauvegarde();
			} 
			catch (Exception e) 
			{
				System.out.println("Erreur de création du catalogue");
				return false;
			}	
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Supprime un catalogue
	 * 
	 * @param nom Le nom du catalogue
	 * @return True ou false en fonction de si le catalogue a été supprimé de la BDD
	 */
	public boolean delete(String nom)
	{
		try 
		{
			Element root = doc.getRootElement();
			Element prod = chercheCatalogue(nom);
			
			if (prod != null) 
			{
				root.removeContent(prod);
				
				// On supprime tous les produits associés
				deleteProduits(nom);
				
				return sauvegarde();
			} 
			else
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur lors de la suppression du catalogue");
			return false;
		}
	}
	
	/**
	 * Supprime tous les produits associés au catalogue
	 * 
	 * @param nomCatalogue Le nom du catalogue
	 */
	private void deleteProduits(String nomCatalogue)
	{
		SAXBuilder sdoc = new SAXBuilder();
		XMLOutputter out = new XMLOutputter();
		
		try
		{
			Document docProduits = sdoc.build(uriProduits);
			
			Element root = docProduits.getRootElement();
			List<Element> lProd = root.getChildren("produit");
			
	        Element[] elementsArray = new Element[lProd.size()];
	        elementsArray = lProd.toArray(elementsArray);

	        // Pour chacun des éléments, on vérifie s'ils appartiennent au catalogue, si oui on les supprime
	        for(Element element : elementsArray)
	        {
				if(element.getChild("catalogue").getValue().equals(nomCatalogue))
				{
					root.removeContent(element);
				}
	        }

			System.out.println("Sauvegarde");
			out.output(docProduits, new PrintWriter(uriProduits));
		}
		catch(Exception e)
		{
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	/**
	 * Récupère tous les catalogues
	 * 
	 * @return La liste de catalogues
	 */
	public List<I_Catalogue> findAll()
	{
		List<I_Catalogue> l = new ArrayList<I_Catalogue>();

		try
		{
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("catalogue");
			
			Iterator<Element> it = lProd.iterator();
					
			while(it.hasNext()) 
			{			
				Element elementTemp = it.next();
				
				String nomP = elementTemp.getAttributeValue("nom");
				l.add(new Catalogue(nomP));
			}	
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur lors de la recherche de tous les catalogues");
		}
		return l;
	}
	
	/**
	 * Récupère le nombre de produits d'un catalogue
	 * 
	 * @return Le nombre de produits
	 */
	public int getNombreProduits(String nomCatalogue)
	{	
		SAXBuilder sdoc = new SAXBuilder();

		int i = 0;
		
		try
		{
			Document docProduits = sdoc.build(uriProduits);
			
			Element root = docProduits.getRootElement();
			List<Element> lProd = root.getChildren("produit");
			
			Iterator<Element> it = lProd.iterator();
					
			while(it.hasNext()) 
			{			
				Element elementTemp = it.next();
				
				if(elementTemp.getChild("catalogue").getValue().equals(nomCatalogue))
				{
					i++;
				}
			}
			return i;
		}
		catch(Exception e)
		{
			System.out.println("Erreur : " + e.getMessage());
			return i;
		}
	}

	/**
	 * Cherche le catalogue dans le fichier XML
	 * 
	 * @param nomCatalogue
	 * @return Le catalogue ou null
	 */	
	private Element chercheCatalogue(String nomCatalogue)
	{
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("catalogue");
		
		Iterator<Element> it = lProd.iterator();
				
		while(it.hasNext()) 
		{			
			Element elementTemp = it.next();
			
			if(elementTemp.getAttributeValue("nom").equals(nomCatalogue))
			{
				return elementTemp;
			}
		}
		return null;
	}
	
	/**
	 * Enregistre les modifications dans le fichier XML
	 * 
	 * @return True ou false en fonction de si les modifications ont été enregistrées dans le fichier
	 */
	private boolean sauvegarde()
	{
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		
		try 
		{
			out.output(doc, new PrintWriter(uriCatalogues));
			return true;
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur de sauvegarde dans le fichier XML");
			System.out.println(e.getMessage());
			return false;
		}
	}
}

