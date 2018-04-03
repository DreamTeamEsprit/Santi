/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.sun.prism.impl.Disposer;
import controllers.ModifprodController;
import controllers.ProduitDetailsController;
import entities.panier;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import main.main;

/**
 *
 * @author marwen b-al
 */
public class Buttonplus extends TableCell<Disposer.Record, Boolean> {

    final Button cellButton = new Button("  +   ");



    public Buttonplus() {

        //Action when the button is pressed
        cellButton.setOnAction((ActionEvent t) -> {
            // get Selected Item
            panier prodcourant = (panier) Buttonplus.this.getTableView().getItems().get(Buttonplus.this.getIndex());
            //remove selected item from the table list
            System.out.println("ID du produit   "+prodcourant.getId());
           
            panierService ps = new panierService();
            ps.modifierPanier(prodcourant, prodcourant.getId());
                                     
            
            try {
                main.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/monpanier.fxml"))));
            } catch (IOException ex) {
                Logger.getLogger(Buttonplus.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }
    }
}
