/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import connexionDatabase.MyDB;
import entities.prodrechercher;
import entities.produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marwen b-al
 */
public class prodrechercherService implements iservice.iprodrechercher
{
     MyDB myDB;
    public prodrechercherService()
    {
        myDB = MyDB.getInstance();
    }
    
    
    @Override
    public void ajouterProduit(prodrechercher prod) {
        
        try {
           
            Statement stm = myDB.getConnexion().createStatement();
            String query = "INSERT INTO `prodrechercher` ( `nom`) VALUES ('"+prod.getNom()+"');";
            stm.executeUpdate(query);
            System.out.println("Ajout OK!");
        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
 
    

    
     @Override
       public prodrechercher selectProduitr (String nom) throws SQLException
    {
       
        prodrechercher bp = new prodrechercher();
        
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from prodrechercher where nom  ='" + nom +" '");
             
            while(rest.next())
            {
                   bp.setId(rest.getInt(1));
            bp.setNom(rest.getString(2));
            bp.setQuantite(rest.getInt(3));
                       System.out.println(bp.toString());

            
            }
            System.out.println(bp.toString());
            return bp;
            
        
        
    }
       
        @Override
    public void modifierProduit(prodrechercher prod,int id){
        
       
        try {

                 PreparedStatement prep;
                 myDB = MyDB.getInstance();
            String req;
          req = "UPDATE `prodrechercher` SET  `quantite` = ?  WHERE id = "+id+"";
            
           prep= myDB.getConnexion().prepareStatement(req);
                        
           // prep.setInt(1, prod.getId());
            prep.setInt(1, prod.getQuantite()+1);
           
            
            prep.executeUpdate();
            System.out.println("Modification OK!");
         } catch (SQLException ex) {
            System.out.println("Problème de Modification");
        }
      }
    
    
     @Override
    public ObservableList<prodrechercher> selectProduit() {
        ObservableList<prodrechercher> produits = FXCollections.observableArrayList();
        try
        {
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from prodrechercher");
        while(rest.next())
        {
            prodrechercher bp = new prodrechercher();
            
            bp.setId(rest.getInt(1));
            bp.setNom(rest.getString(2));
            bp.setQuantite(rest.getInt(3));
           
            
            
            produits.add(bp);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return produits;
    }
}
