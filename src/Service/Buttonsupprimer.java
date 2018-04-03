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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import main.main;

/**
 *
 * @author marwen b-al
 */
public class Buttonsupprimer extends TableCell<Disposer.Record, Boolean> {

    final Button cellButton = new Button("Supprimer");



    public Buttonsupprimer() {

        //Action when the button is pressed
        cellButton.setOnAction((ActionEvent t) -> {
            // get Selected Item
            panier prodcourant = (panier) Buttonsupprimer.this.getTableView().getItems().get(Buttonsupprimer.this.getIndex());
            //remove selected item from the table list
            System.out.println("ID du produit   "+prodcourant.getId());

            ButtonType foo = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
         ButtonType bar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
           Alert alert = new Alert(AlertType.INFORMATION,
        "Etes-Vous Sur De Supprimer Ce Produit ?",
        foo,
        bar);

         alert.setTitle("Suppression");
         Optional<ButtonType> result = alert.showAndWait();

       if (result.isPresent() && result.get() == foo) {
          panierService ps = new panierService();
                           ps.supprimerProduitdupanier(prodcourant);
            try {
                main.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/monpanier.fxml"))));
            } catch (IOException ex) {
                Logger.getLogger(Buttonsupprimer.class.getName()).log(Level.SEVERE, null, ex);
            }

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
