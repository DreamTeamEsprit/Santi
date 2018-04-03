/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entities.prodrechercher;
import entities.produit;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author marwen b-al
 */
public interface iprodrechercher {
        public void ajouterProduit(prodrechercher p);
      public prodrechercher selectProduitr (String nom) throws SQLException;
  public void modifierProduit(prodrechercher prod,int id);
   public ObservableList<prodrechercher> selectProduit();
}
