/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import Model.User;

/**
 *
 * @author said
 */
public class ButtonCell extends TableCell<Record, Boolean> {

    final Button cellButton = new Button("Prendre RDV");

    public ButtonCell() {

        //Action when the button is pressed
        cellButton.setOnAction((ActionEvent t) -> {
            try {
                // get Selected Item
                User Usercourant = (User) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                //remove selected item from the table list

                Santi.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/Choixrdv.fxml"))));

            } catch (IOException ex) {
                Logger.getLogger(ButtonCell.class.getName()).log(Level.SEVERE, null, ex);
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
