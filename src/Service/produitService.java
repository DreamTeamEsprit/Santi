
package Service;

import com.jfoenix.controls.JFXTextField;
import connexionDatabase.MyDB;
import entities.categorie;
import entities.produit;
import java.sql.Connection;
import java.sql.Date;
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
public class produitService  implements iservice.iproduit
{
       MyDB myDB;
    public produitService()
    {
        myDB = MyDB.getInstance();
    }
    
    
    @Override
    public void ajouterProduit(produit prod) {
        
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
            Statement stm = myDB.getConnexion().createStatement();
            String query = "INSERT INTO `produit` ( `categorie`, `user_id`, `libelle`, `description`, `prix`, `remise`, `quantite_dispo`, `quantite`, `prix_livraison`, `marque`, `photo`, `date_fabrication`) VALUES ('"+prod.getCategorie()+"', Null , '"+prod.getLibelle()+"', '"+prod.getDescription()+"', '"+prod.getPrix()+"', '"+prod.getRemise()+"', '"+prod.getQuantiteDispo()+"',Null , '"+prod.getPrixLivraison()+"', '"+prod.getMarque()+"','"+prod.getPhoto()+"', '"+prod.getDateFabrication()+"');";
            stm.executeUpdate(query);
            System.out.println("Ajout OK!");
        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @Override
    public void supprimerProduit(produit bp) 
    {
        PreparedStatement prep;
        try 
        {
            prep = myDB.getConnexion().prepareStatement("delete from produit where libelle= ?");
            prep.setString(1, bp.getLibelle());
            prep.executeUpdate();
            System.out.println("delete OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression!");
        }
        
    }

    @Override
    public ObservableList<produit> selectProduit() {
        ObservableList<produit> produits = FXCollections.observableArrayList();
        try
        {
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from produit");
        while(rest.next())
        {
            produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            produits.add(bp);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return produits;
    }
    
    @Override
    public void modifierProduit(produit prod,int id){
        
       
        try {

                 PreparedStatement prep;
                 myDB = MyDB.getInstance();
            String req;
          req = "UPDATE `produit` SET  `categorie` = ? , `libelle` = ?, `description` = ?, `prix` =?, `remise` = ?, `quantite_dispo` = ?, `prix_livraison` = ?, `marque` = ?, `photo` = ?, `date_fabrication` = ? WHERE id = "+id+"";
            
           prep= myDB.getConnexion().prepareStatement(req);
                        
           // prep.setInt(1, prod.getId());
            prep.setString(1, prod.getCategorie());
            prep.setString(2, prod.getLibelle());
            prep.setString(3, prod.getDescription());
            prep.setFloat(4, prod.getPrix());
            prep.setFloat(5, prod.getRemise());
            prep.setInt(6, prod.getQuantiteDispo());
            prep.setFloat(7, prod.getPrixLivraison());
            prep.setString(8, prod.getMarque());
            prep.setString(9, prod.getPhoto());
            prep.setString(10,  prod.getDateFabrication());
            
            prep.executeUpdate();
            System.out.println("Modification OK!");
         } catch (SQLException ex) {
            System.out.println("Problème de Modification");
        }
      }

  /*  @Override
    public ObservableList<produit> selectProduitBynom(String nom) {
        ObservableList<produit> produits = FXCollections.observableArrayList();
        try
        {
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from produit where libelle like "+nom+"");
        while(rest.next())
        {
            produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(3));
            bp.setDescription(rest.getString(4));
            bp.setPrix(rest.getFloat(5));
            bp.setRemise(rest.getFloat(6));
            bp.setQuantiteDispo(rest.getInt(7));
            bp.setPrixLivraison(rest.getFloat(8));
            bp.setMarque(rest.getString(9));
            bp.setPhoto(rest.getString(10));   
            bp.setDateFabrication(rest.getDate(11));
            
            
            produits.add(bp);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return produits;
    }*/

    @Override
    public produit selectProduit(int id) throws SQLException
    {
        
        produit bp = new produit();
        
        Statement stm = myDB.getConnexion().createStatement();
        ResultSet rest=stm.executeQuery("select * from produit where id ="+id+"");
            
            while(rest.next())
            {
                   bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            }
            System.out.println(bp.toString());
            return bp;
            
        
        
    }

    @Override
    public void supprimerProduit(int id) {
        
        PreparedStatement prep;
        try 
        {
            prep = myDB.getConnexion().prepareStatement("delete from produit where id = ?");
            prep.setInt(1, id);
            prep.executeUpdate();
            System.out.println("delete OK!");
        }
        catch (SQLException ex) 
        {
            System.out.println("Probleme de suppression!");
        }
        
    }
    @Override
public List<produit> getAll(String libelle) {
        PreparedStatement preparedStatement;

        List<produit> prodAL = new ArrayList<>();
        
        String req = "select * from produit where  libelle like '%" + libelle + "%'";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            prodAL.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodAL;
    }
 
@Override
public List<produit> getAllpluschere() {
        PreparedStatement preparedStatement;

        List<produit> prodAL = new ArrayList<>();
        
        String req = "select * from produit ORDER BY prix DESC";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            prodAL.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodAL;
    }
  

@Override
public List<produit> getAllmoinschere() {
        PreparedStatement preparedStatement;

        List<produit> prodAL = new ArrayList<>();
        
        String req = "select * from produit ORDER BY prix ";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            prodAL.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodAL;
    }
   @Override
public List<produit> getAllparcategorie(int  idcat) {
        PreparedStatement preparedStatement;

        List<produit> prodAL = new ArrayList<>();
        
        String req = "select * from produit p  where p.categorie = " +idcat+"";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            prodAL.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodAL;
    }



@Override
public List<produit> selectProduitPrix(double  prix) {
        PreparedStatement preparedStatement;

        List<produit> prodAL = new ArrayList<>();
        double prixa = prix - 10 ;
        double prixb = prix + 10 ;
        String req = "select * from produit where prix Between  "+prixa+ " and " +prixb+"";
       
            
        try {
            preparedStatement = myDB.getConnexion().prepareStatement(req);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                 produit bp = new produit();
            
            bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
            
            
            prodAL.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodAL;
    }



   @Override
    public  produit rechercherparImage(String image) throws SQLException   {
       
        
        
        produit bp = new produit();
        
      
            String sql = "select * from produit where photo  ='" + image +" '";
                    myDB = MyDB.getInstance();
            Connection stm = myDB.getConnexion();
            PreparedStatement ps = stm.prepareStatement(sql);
            ResultSet rest = ps.executeQuery();
              while(rest.next())
        {
                  bp.setId(rest.getInt(1));
            bp.setCategorie(rest.getString(2));
            bp.setLibelle(rest.getString(4));
            bp.setDescription(rest.getString(5));
            bp.setPrix(rest.getFloat(6));
            bp.setRemise(rest.getFloat(7));
            bp.setQuantiteDispo(rest.getInt(8));
            bp.setPrixLivraison(rest.getFloat(10));
            bp.setMarque(rest.getString(11));
            bp.setPhoto(rest.getString(12));   
            bp.setDateFabrication(rest.getString(13));
 
        }
 
             return bp;   
}

}
