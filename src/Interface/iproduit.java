/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXTextAreaSkin;
import entities.panier;
import entities.produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marwen b-al
 */
public interface iproduit {
      
    public void ajouterProduit(produit p);
    public void supprimerProduit(produit p);
    public void supprimerProduit(int id);
    public List<produit> selectProduit();
    public List<produit> getAll( String libelle) ;
    public produit selectProduit(int id)throws SQLException;
    public void modifierProduit(produit p,int id);
    public List<produit> getAllpluschere();
    public List<produit> getAllmoinschere();
      public List<produit> getAllparcategorie( int idcat) ;
     public List<produit>  selectProduitPrix(double prix) ;
     public  produit rechercherparImage(String image) throws SQLException ;
     
}
