/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.jfoenix.controls.JFXTextField;
import connexionDatabase.MyDB;
import entities.categorie;
import entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marwen b-al
 */
public class CategorieService implements iservice.icategorie
{
     MyDB myDB;
    public CategorieService()
    {
        myDB = MyDB.getInstance();
    } 


  

  



    @Override
    public void ajouterCategorie(categorie cat) {
        
          try {
            
            Statement stm = myDB.getConnexion().createStatement();
            String query = "INSERT INTO `categorie` (`libelle`, `description`) VALUES ('"+cat.getLibelle()+"', '"+cat.getDescription()+"');";
            stm.executeUpdate(query);
            System.out.println("Ajout OK!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
    
    
    
     
     @Override
    public void modifierCategorie (categorie cat , int id)
    {
           

        try {

                 PreparedStatement prep;
                 myDB = MyDB.getInstance();
            String req;
          req = "UPDATE `categorie` SET `libelle` = ?, `description` = ? WHERE id = "+id+"";
            
           prep= myDB.getConnexion().prepareStatement(req);
                        
            prep.setString(1, cat.getLibelle());
            prep.setString(2, cat.getDescription());
       
            
            prep.executeUpdate();
            System.out.println("Modification OK!");
         } catch (SQLException ex) {
            System.out.println("Problème de Modification");
        }
      }
      

  /*   @Override
    public ObservableList<categorie> selectCategorieBynom(String nom) {
        ObservableList<categorie> categories = FXCollections.observableArrayList();
        try
        {  
                 myDB = MyDB.getInstance();
       String req = "select * from produit where libelle = ?";
            PreparedStatement pst = myDB.getConnexion().prepareStatement(req);
            pst.setString(1, "%"+nom+"%");
            ResultSet rest = pst.executeQuery();
        while(rest.next())
        {
            categorie bp = new categorie();
            
            bp.setId(rest.getInt(1));
            bp.setLibelle(rest.getString(2));
            bp.setDescription(rest.getString(3));
            
            
            
            categories.add(bp);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return categories;
    }
*/

    
    
      @Override
    public void supprimerCategorie(int id) {
        myDB = MyDB.getInstance();
          PreparedStatement prep1;
        PreparedStatement prep;
      
         try 
        {
                                

         prep1 = myDB.getConnexion().prepareStatement("delete from produit where categorie = "+id+"");
         prep1.executeUpdate(); 
            
         
            
            System.out.println("delete produit OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression produit!");
        }
        try 
        {
                                

       
            prep = myDB.getConnexion().prepareStatement("delete from categorie where id = ?");
            prep.setInt(1, id);
            prep.executeUpdate();
            
            System.out.println("delete categorie OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression! categorie");
        }
        
    }
    
       @Override
public List<categorie> getAll(JFXTextField libelle) {
        PreparedStatement preparedStatement;

        List<categorie> categories = new ArrayList<>();
        
        String req = "select * from categorie where  libelle like '%" + libelle.getText() + "%'";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 categorie bp = new categorie();
            
            bp.setId(rest.getInt(1));
            bp.setLibelle(rest.getString(2));
            bp.setDescription(rest.getString(3));
            
            
            
            categories.add(bp);
            
            
        
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

   @Override
    public  categorie rechercherparlibelle(String libelle) throws SQLException   {
       
        
        
        categorie bp = new categorie();
        
      
            String sql = "SELECT * FROM `categorie`WHERE libelle like '%" + libelle + "%'";
                    myDB = MyDB.getInstance();
            Connection stm = myDB.getConnexion();
            PreparedStatement ps = stm.prepareStatement(sql);
            ResultSet rest = ps.executeQuery();
              while(rest.next())
        {
            bp.setId(rest.getInt(1));
            bp.setLibelle(rest.getString(2));
            bp.setDescription(rest.getString(3));
 
        }
 
             return bp;   
}

}
