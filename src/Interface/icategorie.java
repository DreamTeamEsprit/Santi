/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXTextField;
import entities.categorie;
import entities.produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marwen b-al
 */
public interface icategorie {
      public void ajouterCategorie(categorie cat);
        public void modifierCategorie(categorie cat , int id  );
        public List<categorie> getAll( JFXTextField libelle);
        public void supprimerCategorie(int id);   
         public  categorie rechercherparlibelle(String libelle)  throws SQLException ;
    
}
