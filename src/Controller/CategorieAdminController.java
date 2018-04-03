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
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import connexionDatabase.MyDB;
import entities.categorie;
import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.ButtonCell;
import service.CategorieService;
import service.produitService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class CategorieAdminController implements Initializable {

         private static int idBP;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<categorie> tablecat;
    @FXML
    private TableColumn<categorie, Integer> id;
    @FXML
    private TableColumn<categorie, String> libelle;
    @FXML
    private TableColumn<categorie, String> description;
    @FXML
    private JFXTextField recherchercat;
    @FXML
    private JFXButton ajoutercat;
    
      static categorie categorie ;
    public ObservableList<categorie>data=FXCollections.observableArrayList();
 
    @FXML
    private Button Rechercher;
    @FXML
    private JFXTextField lib;
    @FXML
    private JFXTextField desc;
    @FXML
    private JFXButton modiff;
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

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
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
               ResultSet rest=stm.executeQuery("select * from categorie where 1");
        categorie = new categorie();
               while(rest.next())
               {
                   
                   data.add(new categorie(rest.getInt(1),rest.getString(2),rest.getString(3)));
                   
               }
           

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       id.setCellValueFactory(new PropertyValueFactory<>("id") );
       libelle.setCellValueFactory(new PropertyValueFactory<>("libelle") );
       description.setCellValueFactory(new PropertyValueFactory<>("description") );
 

        tablecat.setItems(data);  
        
    }    

    @FXML
    private void ajoutercat(ActionEvent event) throws IOException {
         
       if(lib.getText().length()==0)
        {
            lib.setStyle("-fx-text-inner-color: red");
            lib.setStyle("-fx-prompt-text-fill: red");
            lib.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            
            alert.setTitle("Attention");
            alert.setContentText("Veuillez Saisir une libelle du produit!");
            alert.showAndWait();
            
            
        }
        else if(desc.getText().length()==0)
        {
            
            desc.setStyle("-fx-text-inner-color: red");
            desc.setStyle("-fx-prompt-text-fill: red");
            desc.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une description du produit !");
            alert.showAndWait();
            
            
            
        }
       
        else
        {


        String libelleA = lib.getText();
        String descriptionA = desc.getText();

       
            categorie cat = new categorie();
            
            cat.setLibelle(libelleA);
            cat.setDescription(descriptionA);

            
            service.CategorieService bps = new CategorieService();
            bps.ajouterCategorie(cat);
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Categorie ajouté avec succes!");
            alert.showAndWait();
            
            Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/categorieAdmin.fxml"));
            Parent root = loader.load();
            

            Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/categorieadmin.css").toExternalForm());
            stageS.show();
    }
    }
  @FXML
    private void showDetailsC(ContextMenuEvent event) throws IOException {
          System.out.println(tablecat.getSelectionModel().getSelectedItem().toString());
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem info = new MenuItem("Supprimer");
        
       
        contextMenu.getItems().add(info);
        tablecat.setContextMenu(contextMenu);
        
        info.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) 
            {Alert alertinf = new Alert(Alert.AlertType.INFORMATION);
          
                        alertinf.setTitle("Suppression du Categorie");
                    alertinf.setHeaderText("les produits associé a cette categorie sera supprimé !");
                    alertinf.setContentText("Vous êtes sur de supprimer cette categorie ?");
                    Optional<ButtonType> result = alertinf.showAndWait();
                    if (result.get() == ButtonType.OK) {
                idBP=tablecat.getSelectionModel().getSelectedItem().getId(); 
                categorie a =tablecat.getSelectionModel().getSelectedItem();
                  
        
            service.CategorieService bps = new CategorieService();
            bps.supprimerCategorie(idBP);
            
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.setTitle("Succès");
            alert.setContentText("Categorie supprimé avec succes! ");
            alert.showAndWait();
            tablecat.getItems().remove(a);
            tablecat.refresh();
                    }
                    
                 
            }
        });
  
    }

    @FXML
    private void recherchercat(ActionEvent event) {
        
        
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
      
       CategorieService cs = new CategorieService();
        List<categorie> list = cs.getAll( recherchercat);
        ObservableList<categorie> items = FXCollections.observableArrayList(list);
        tablecat.setItems(items);
    }

    @FXML
    private void modifcatt(MouseEvent event) throws IOException {
         categorie a =tablecat.getSelectionModel().getSelectedItem();
         lib.setText(a.getLibelle());
       desc.setText(a.getDescription()); 
       
     
      
    }

    @FXML
    private void modiffbutton(ActionEvent event) throws IOException {
          categorie cat = new categorie();
              categorie a =tablecat.getSelectionModel().getSelectedItem();
            
            cat.setLibelle(lib.getText());
            cat.setDescription(desc.getText());
       
            
            service.CategorieService bps = new CategorieService();
            bps.modifierCategorie(cat, a.getId());
          
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Categorie modifé avec succes!");
            alert.showAndWait();
            
            Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/categorieAdmin.fxml"));
            Parent root = loader.load();
            

            Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/categorieadmin.css").toExternalForm());
            stageS.show();
        
    }
    
    
    
   
    
}

