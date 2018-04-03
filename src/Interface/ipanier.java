/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entities.panier;
import entities.prodrechercher;
import entities.produit;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author marwen b-al
 */
public interface ipanier {
            public void ajouterPanier(produit p);
      public panier selectProduitpanier (int  id) throws SQLException;
  public void modifierPanier(panier prod,int id);
   public ObservableList<panier> selectPanier();
    public int  selectQuantite (produit p);
     public void supprimerProduitdupanier (panier bp) ;
      public void modifiermoins (panier prod,int id);
          public void supprimerPanier() ;

    
}
