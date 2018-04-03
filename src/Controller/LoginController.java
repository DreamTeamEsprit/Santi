/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginbutton(ActionEvent event) {
          try {
                
                   if ( login.getText().equals("marwen") && password.getText().equals("marwen") )
       {
           try {
           
            
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from produit where 1");
                
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
                 if (bp.getQuantiteDispo() < 10)
                 {
                   Notifications not = Notifications.create()
                           .title("Attention !")
                           .text("Il vous Reste      "+bp.getQuantiteDispo()+"      pieces du produit      "+bp.getLibelle())
                           .hideAfter(Duration.seconds(25)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                       }
                   });
                         
                   
                   not.showError();
                 }
                   
               }
           

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/espaceAdmin.fxml"));
                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               
	stage.setScene(scene);
                stage.show();
            }
                   else if (login.getText().equals("user") && password.getText().equals("user"))
                   {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/views/espaceUser.fxml"));
                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               
	       stage.setScene(scene);
                stage.show();
                   
                   
                   }
            else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Mot De Passe ou login est Incorrecte !!!!!");
        alert.show();
       }
            
            
            }
            catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
       

 
           
                   
        
           
    }    
    
    
}
