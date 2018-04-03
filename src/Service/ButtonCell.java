
package Service;

import com.sun.prism.impl.Disposer.Record;
import controllers.AjouterProdController;
import controllers.ModifprodController;
import controllers.ProduitDetailsController;
import entities.produit;
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
public class ButtonCell extends TableCell<Record, Boolean> {

    final Button cellButton = new Button("Modifier");



    public ButtonCell() {

        //Action when the button is pressed
        cellButton.setOnAction((ActionEvent t) -> {
            // get Selected Item
            produit prodcourant = (produit) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            //remove selected item from the table list
            System.out.println("ID du produit   "+prodcourant.getId());
            ModifprodController l = new ModifprodController();
                                      
                                       l.redirect(String.valueOf(prodcourant.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
            main.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/modifprod.fxml"))));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                     
            
            // main.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/modifprod.fxml"))));
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
