/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import connexionDatabase.MyDB;
import entities.panier;
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
public class panierService implements iservice.ipanier
{
     MyDB myDB;
    public panierService()
    {
        myDB = MyDB.getInstance();
    }
    
    @Override
    public void ajouterPanier(produit p) {

    
        try {
            
            Statement stm = myDB.getConnexion().createStatement();
            String query = "INSERT INTO `panier` (`id`, `idproduit`, `quantite`, `nomproduit`, `prixproduit`, `soustotal`) VALUES (NULL,"+p.getId()+" , '1','"+p.getLibelle()+"' ,"+p.getPrix()+" ,"+p.getPrix()+");";
            stm.executeUpdate(query);
            System.out.println("Ajout OK!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @Override
    public panier selectProduitpanier(int id) throws SQLException {

        panier bp = new panier();
        
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from panier where idproduit  ='" + id +" '");
             
            while(rest.next())
            {
                   bp.setId(rest.getInt(1));
            bp.setIdproduit(rest.getInt(2));
            bp.setQuantite(rest.getInt(3));
             bp.setNomproduit(rest.getString(4));
              bp.setPrixproduit(rest.getFloat(5));
               bp.setSoustotal(rest.getFloat(6));
                       System.out.println(bp.toString());

            
            }
            System.out.println(bp.toString());
            return bp;  
    }

    @Override
    public void modifierPanier(panier prod, int id) {
       float a = prod.getPrixproduit() * (prod.getQuantite()+1);
        try {

                 PreparedStatement prep;
                 myDB = MyDB.getInstance();
            String req;
          req = "UPDATE `panier` SET `quantite` = ? , `soustotal` = ?   WHERE id = "+id+"";
            
           prep= myDB.getConnexion().prepareStatement(req);
                        
           // prep.setInt(1, prod.getId());
            prep.setInt(1, prod.getQuantite()+1);
            prep.setFloat(2, a );
           
            
            prep.executeUpdate();
            System.out.println("Modification OK!");
         } catch (SQLException ex) {
            System.out.println("Problème de Modification");
        }
    }

    @Override
    public ObservableList<panier> selectPanier() {
           ObservableList<panier> paniers = FXCollections.observableArrayList();
        try
        {
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from panier");
        while(rest.next())
        {
            panier bp = new panier();
            
            bp.setId(rest.getInt(1));
            bp.setIdproduit(rest.getInt(2));
            bp.setQuantite(rest.getInt(3));
               bp.setNomproduit(rest.getString(4));
              bp.setPrixproduit(rest.getFloat(5));
               bp.setSoustotal(rest.getFloat(6));
            
            
            paniers.add(bp);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return paniers;
    }
    
    
    @Override
    public int  selectQuantite (produit p) {

      panier bp = new panier();
      try
        {
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select quantite from panier where idproduit = "+p.getId()+"");
        while(rest.next())
        {
            
            
            bp.setId(rest.getInt(1));
            bp.setIdproduit(rest.getInt(2));
            bp.setQuantite(rest.getInt(3));
              bp.setNomproduit(rest.getString(4));
              bp.setPrixproduit(rest.getFloat(5));
               bp.setSoustotal(rest.getFloat(6));
            
            

        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return bp.getQuantite();
       
    }
    
      @Override
    public void supprimerProduitdupanier (panier bp) 
    {
        PreparedStatement prep;
        try 
        {
            prep = myDB.getConnexion().prepareStatement("delete from panier where id = ?");
            prep.setInt(1, bp.getId());
            prep.executeUpdate();
            System.out.println("delete OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression!");
        }
        
    }
     @Override
   public void modifiermoins (panier prod,int id) {
       float a = prod.getPrixproduit() * (prod.getQuantite()- 1);
        try {

                 PreparedStatement prep;
                 myDB = MyDB.getInstance();
            String req;
          req = "UPDATE `panier` SET `quantite` = ? , `soustotal` = ?   WHERE id = "+id+"";
            
           prep= myDB.getConnexion().prepareStatement(req);
                        
           // prep.setInt(1, prod.getId());
            prep.setInt(1, prod.getQuantite()- 1);
            prep.setFloat(2, a );
           
            
            prep.executeUpdate();
            System.out.println("Modification OK!");
         } catch (SQLException ex) {
            System.out.println("Problème de Modification");
        }
    }
   
      @Override
    public void supprimerPanier() 
    {
        PreparedStatement prep;
        try 
        {
            prep = myDB.getConnexion().prepareStatement(" TRUNCATE   panier ");
        
            prep.executeUpdate();
            System.out.println("delete OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression!");
        }
        
    }
}
