/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.prism.impl.Disposer;
import connexionDatabase.MyDB;
import entities.prodrechercher;
import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import service.ButtonCell;
import service.prodrechercherService;
import service.produitService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class RapportAdminController implements Initializable {

    @FXML
    private JFXHamburger ham1;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<prodrechercher> table;
    @FXML
    private TableColumn<prodrechercher, Integer> id;
    @FXML
    private TableColumn<prodrechercher, String> nom;
    @FXML
    private TableColumn<prodrechercher, Integer> quantite;
    @FXML
    private TableColumn<?, ?> action;
       static prodrechercher produit ;
    public ObservableList<prodrechercher>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
              try {
             AnchorPane box = FXMLLoader.load(getClass().getResource("/views/drawerContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(EspaceAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(ham1);
        transition.setRate(-1);
        ham1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            }
            else
            {
                drawer.open();
            }
                
        });
    
        
          try {
           
            
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from prodrechercher ORDER BY quantite Desc");
               prodrechercher pr = new prodrechercher();
               while(rest.next())
               {
                   
                   data.add(new prodrechercher(rest.getInt(1),rest.getString(2),rest.getInt(3)));
                   
               }
           

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            
          id.setCellValueFactory(new PropertyValueFactory<>("id") );
       nom.setCellValueFactory(new PropertyValueFactory<>("nom") );
       quantite.setCellValueFactory(new PropertyValueFactory<>("quantite") );


                              
                              
        table.setItems(data);
    }    
    
}
