/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.restfb.types.Notification;
import connexionDatabase.MyDB;
import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class EspaceAdminController implements Initializable {
    
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger ham1;
    @FXML
    private AnchorPane pane1;
  private  Label l = new Label();
    @FXML
    private JFXButton nbrprod;
    @FXML
    private JFXButton nbrcat;
    @FXML
    private JFXButton chiffreaffaire;
    private Scene scene;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        
        
        
      
    }

    @FXML
    private void nbrprod(ActionEvent event) {
           try {
           
             int i=0; 
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from produit where 1");
                   produit bp = new produit();
               while(rest.next())
               {
              i=i+1 ;
                 
               }
               
             Notifications not = Notifications.create()
                           .title("Nombre Des Produits")
                           .text(i+"   Produits Disponible")
                           .hideAfter(Duration.seconds(25)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                          
                       }
                   });
                         
                   
                   not.showInformation();

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void nbrcat(ActionEvent event) {
          try {
           
             int i=0; 
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from categorie where 1");
                   produit bp = new produit();
               while(rest.next())
               {
              i=i+1 ;
                 
               }
               
             Notifications not = Notifications.create()
                           .title("Nombre Des Categories")
                           .text(i+"   Categorie Disponible")
                           .hideAfter(Duration.seconds(25)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                           }
                   });
                         
                   
                   not.showInformation();

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void chiffreaffaire(ActionEvent event) {
        
           try {
           
             float total=0; 
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from produit where 1");
                   produit bp = new produit();
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
             
                 float st = bp.getPrix()*bp.getQuantiteDispo();
                 
                 total = total +st ;
                 
               }
               
             Notifications not = Notifications.create()
                           .title("chiffre d'affaires ")
                           .text(total+"    Dt")
                           .hideAfter(Duration.seconds(25)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                       }
                   });
                         
                   
                   not.showInformation();

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
                
  
    
                



