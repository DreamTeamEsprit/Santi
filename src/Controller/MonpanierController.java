/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.prism.impl.Disposer;
import connexionDatabase.MyDB;
import entities.panier;
import entities.produit;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Insets; 
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.main;
import service.ButtonCell;
import service.Buttonmoins;
import service.Buttonplus;
import service.Buttonsupprimer;
import service.panierService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class MonpanierController implements Initializable {

    @FXML
    private JFXButton deconnexion;
    @FXML
    private TableView<panier> table;
    @FXML
    private TableColumn<panier, String> nom;
    @FXML
    private TableColumn<Disposer.Record, Boolean> moins;
    @FXML
    private TableColumn<panier, Integer> quantite;
    @FXML
    private TableColumn<Disposer.Record, Boolean> plus;
    @FXML
    private TableColumn<panier, Float> soustotal;
    @FXML
    private TableColumn<Disposer.Record, Boolean> supprimer;
         static panier panier ;
    public ObservableList<panier>data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<panier, Float> prix;
    @FXML
    private JFXButton siteweb;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton payment;
    @FXML
    private JFXButton viderpanier;
    @FXML
    private TextField nbrprod;
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        nbrprod.setText(""+nombre());
           try {
           
            
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from panier where 1");
               panier = new panier();
               while(rest.next())
               {
                   
                   data.add(new panier(rest.getInt(1),rest.getInt(2),rest.getInt(3),rest.getString(4),rest.getFloat(5),rest.getFloat(6)));
                     
          
               
               }
           

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      nom.setCellValueFactory(new PropertyValueFactory<>("nomproduit") );
       quantite.setCellValueFactory(new PropertyValueFactory<>("quantite") );

        prix.setCellValueFactory(new PropertyValueFactory<>("prixproduit") );
          soustotal.setCellValueFactory(new PropertyValueFactory<>("soustotal") );
        supprimer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        supprimer.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new Buttonsupprimer();
                
                
            }

        });      
         moins.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        moins.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new Buttonmoins();
            }

        });       
         plus.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        plus.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new Buttonplus();
            }

        });       
                              
        table.setItems(data );
        

    
}
    
    
    private int nombre ()
    {
         MyDB myDB = MyDB.getInstance();
           int a = 0;  
         Statement stm;
        try {
            stm = myDB.getConnexion().createStatement();
             ResultSet rest=stm.executeQuery("select * from panier where 1");
                     
             while(rest.next())
               {
    
        
            a = rest.getInt(3) + a ;
            
               }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
      return a ;
    }
    
    
    
    
    
    @FXML
    private void deconnexion(ActionEvent event)  throws IOException {
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root = loader.load();
        root.setId("pane");
        
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/login.css").toExternalForm());
        stage.show();
    }

    @FXML
    private void siteweb(ActionEvent event) throws URISyntaxException, IOException {
     Desktop.getDesktop().browse(new URI("http://localhost/SanteEtBienEtre1.0/web/app_dev.php"));
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/espaceUser.fxml"));
        Parent root = loader.load();
        root.setId("pane");
        
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/espceuser.css").toExternalForm());
        stage.show();
    }

      @FXML
    private void viderpanier(ActionEvent event) throws IOException {
        
        
        
            ButtonType foo = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
         ButtonType bar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
           Alert alert = new Alert(Alert.AlertType.INFORMATION,
        "Etes-Vous Sur De Supprimer Tous Les Produits ?",
        foo,
        bar);

         alert.setTitle("Suppression");
         Optional<ButtonType> result = alert.showAndWait();

       if (result.isPresent() && result.get() == foo) {
          
    service.panierService p = new panierService();
        p.supprimerPanier();
        
            }
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/monpanier.fxml"));
        Parent root = loader.load();
        root.setId("pane");
        
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/monpanier.css").toExternalForm());
        stage.show();
        
    }
    
    
    
    @FXML
    private void payment(ActionEvent event) {
    }

  

  
    
}
