/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.prism.impl.Disposer.Record;
import connexionDatabase.MyDB;
import entities.produit;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane ;
import javafx.util.Callback;
import service.ButtonCell;
import service.produitService;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class ProdAdminController extends ListView<String> implements Initializable {

   
    @FXML
    private JFXDrawer drawer;
      @FXML
    private AnchorPane pane1;
    private JFXTextField search;


    @FXML
    private JFXButton ajouterprod;
  
    @FXML
    private JFXHamburger ham1;
    @FXML
    private TableView<produit> tableview;
    @FXML
    private JFXButton btnX;
        
           private static int idBP;

    public static int getIdBP() {
        return idBP;
    }
    @FXML
    private TableColumn<produit, String> libelle;
    @FXML
    private TableColumn<produit, String> categorie;
    @FXML
    private TableColumn<produit, Integer> quantitedispo;
    @FXML
    private TableColumn<produit, Image> photo;
        static produit produit ;
    public ObservableList<produit>data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<produit, Integer> id;
    @FXML
    private TableColumn<Record, Boolean> action;
    @FXML
    private JFXTextField searchlibelle;
    private JFXTextField categorieFX;
    private JFXTextField LibelleFX;
    private JFXTextField descFx;
    private JFXTextField prixfx;
    private JFXTextField remisefx;
    private JFXTextField qdfx;
    private JFXTextField prixfabfx;
    private JFXTextField marquefx;
    private JFXDatePicker satefx;
      private String fileName = "No picture";
    @FXML
    private Label fileimage;
    @FXML
    private TableColumn<produit, String> description;
    @FXML
    private TableColumn<produit, Float> peix;
    @FXML
    private TableColumn<produit, Float> remise;
    @FXML
    private TableColumn<produit, String> marque;
    @FXML
    private TableColumn<produit, Float> prixlivraison;
    @FXML
    private TableColumn<produit, Date> datefavrication;
    @FXML
    private JFXButton excel;
    

    /**
     * Initializes the controller class.
     */

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
          try {
           
            
            MyDB myDB = MyDB.getInstance();
            Statement stm = myDB.getConnexion().createStatement();
               ResultSet rest=stm.executeQuery("select * from produit where 1");
               produit = new produit();
               while(rest.next())
               {
                   
                   data.add(new produit(rest.getInt(1),rest.getString(2),rest.getString(4),rest.getString(5),rest.getFloat(6),rest.getFloat(7),rest.getInt(8),rest.getFloat(10),rest.getString(11),rest.getString(12),rest.getString(13)));
                   
               }
           

            
        } catch (SQLException ex) {
            Logger.getLogger(produit.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          id.setCellValueFactory(new PropertyValueFactory<>("id") );
       categorie.setCellValueFactory(new PropertyValueFactory<>("categorie") );
       libelle.setCellValueFactory(new PropertyValueFactory<>("libelle") );
       quantitedispo.setCellValueFactory(new PropertyValueFactory<>("quantiteDispo") );

       photo.setCellValueFactory(new PropertyValueFactory<>("photo") );
         description.setCellValueFactory(new PropertyValueFactory<>("description") );
           peix.setCellValueFactory(new PropertyValueFactory<>("prix") );
             remise.setCellValueFactory(new PropertyValueFactory<>("remise") );
               marque.setCellValueFactory(new PropertyValueFactory<>("marque") );
                              prixlivraison.setCellValueFactory(new PropertyValueFactory<>("prixLivraison") );
                              datefavrication.setCellValueFactory(new PropertyValueFactory<>("dateFabrication") );
action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });
                              
                              
        tableview.setItems(data);
        
    }         
        
    
      
    

    
        

    
    


    @FXML
    private void showDetailsP(ContextMenuEvent event) throws IOException {
          System.out.println(tableview.getSelectionModel().getSelectedItem().toString());
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem info = new MenuItem("Supprimer");
        
       
        contextMenu.getItems().add(info);
        tableview.setContextMenu(contextMenu);
        
        info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {  Alert alertinf = new Alert(AlertType.INFORMATION);
          
                        alertinf.setTitle("Suppression du produit");
                    alertinf.setHeaderText("Suppression du produit");
                    alertinf.setContentText("Vous êtes sur de supprimer ce Produit ?");
                    Optional<ButtonType> result = alertinf.showAndWait();
                    if (result.get() == ButtonType.OK) {
                          idBP=tableview.getSelectionModel().getSelectedItem().getId(); 
                produit a =tableview.getSelectionModel().getSelectedItem();
                   
               
                 
            service.produitService bps = new produitService();
            bps.supprimerProduit(idBP);
            
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.setTitle("Succès");
            alert.setContentText("Produit supprimé avec succes!");
            alert.showAndWait();
            tableview.getItems().remove(a);
             tableview.refresh();
                    
              

            
           }     
                 
            }
        });
  
    }

    @FXML
    private void close(ActionEvent event) {
    }

       @FXML
    private void ajouterprod(ActionEvent event) throws IOException {
      
      Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ajouterprod.fxml"));
        Parent root = loader.load();
        root.setId("pane");
        
        Scene scene1 = new Scene(root );
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/ajouterprod.css").toExternalForm());
        
        stage.show();
    }

    @FXML
    private void search(ActionEvent event) {
     id.setCellValueFactory(new PropertyValueFactory<>("id") );
       categorie.setCellValueFactory(new PropertyValueFactory<>("categorie") );
       libelle.setCellValueFactory(new PropertyValueFactory<>("libelle") );
       quantitedispo.setCellValueFactory(new PropertyValueFactory<>("quantiteDispo") );
       photo.setCellValueFactory(new PropertyValueFactory<>("photo") );
     description.setCellValueFactory(new PropertyValueFactory<>("description") );
           peix.setCellValueFactory(new PropertyValueFactory<>("prix") );
             remise.setCellValueFactory(new PropertyValueFactory<>("remise") );
               marque.setCellValueFactory(new PropertyValueFactory<>("marque") );
                              prixlivraison.setCellValueFactory(new PropertyValueFactory<>("prixLivraison") );
                              datefavrication.setCellValueFactory(new PropertyValueFactory<>("dateFabrication") );

      String a =  searchlibelle.getText();
      produitService bp = new produitService();
        List<produit> list = bp.getAll( a);
        ObservableList<produit> items = FXCollections.observableArrayList(list);
        tableview.setItems(items);
        tableview.refresh();
       
        

    
}
    
      @FXML
    private void exportAction(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        //set extension filter
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Excel Files(*.xls)","*.xls");
        chooser.getExtensionFilters().add(filter);
        //show save dialog
        File file=chooser.showSaveDialog(excel.getScene().getWindow());
        if(file!=null)
        {
            saveXLSFile(file);
        }
        
    }

 
 
    private void saveXLSFile(File file ) {
    
      try {
            FileOutputStream fileOut;
            fileOut=new FileOutputStream(file);
            HSSFWorkbook workbook=new HSSFWorkbook();
            HSSFSheet workSheet=workbook.createSheet("sheet 0");
            HSSFRow row1=workSheet.createRow((short)0);
            row1.createCell(0).setCellValue("id_produit");
            row1.createCell(1).setCellValue("categorie");
            row1.createCell(2).setCellValue("libelle");
            row1.createCell(3).setCellValue("description");
            row1.createCell(4).setCellValue("prix");
            row1.createCell(5).setCellValue("Remise");
            row1.createCell(6).setCellValue("quantiteDispo");
            row1.createCell(7).setCellValue("prixLivraison");
            row1.createCell(8).setCellValue("Marque");
            row1.createCell(9).setCellValue("Photo");
            row1.createCell(9).setCellValue("date de fabrication");
            
             HSSFRow row2;
            /*con = CupCakeDBConnection.getInstance().getConnection();
           ResultSet rs = con.createStatement().executeQuery("SELECT * FROM produit ");*/
		  
			String req="SELECT * FROM produit where 1";
			PreparedStatement pre=MyDB.getInstance().getConnexion().prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int a =rs.getRow();
                row2=workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(rs.getInt(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(4));
                row2.createCell(3).setCellValue(rs.getString(5));
                row2.createCell(4).setCellValue(rs.getFloat(6));
                row2.createCell(5).setCellValue(rs.getFloat(7));
                row2.createCell(6).setCellValue(rs.getInt(8));
                row2.createCell(7).setCellValue(rs.getFloat(10));
                row2.createCell(8).setCellValue(rs.getString(11));
                row2.createCell(9).setCellValue(rs.getDate(13));
                
                
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            Notifications not = Notifications.create()
                           .title("Succes !")
                           .text("Le fichier est Telecharger avec succes \n Cliquer pour l'ouvrir ")
                           .hideAfter(Duration.seconds(10)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                           Desktop dt = Desktop.getDesktop();
                           try {
                               dt.open(file);
                           } catch (IOException ex) {
                               Logger.getLogger(ProdAdminController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                   });
                         
                   
                   not.showConfirm();
           
        } catch (SQLException|IOException e) {
          
            System.err.println(e);
            Notifications not = Notifications.create()
                           .title("Erreur !")
                           .text("Le fichier n'est pas Telecharger ")
                           .hideAfter(Duration.seconds(5)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                       }
                   });
                         
                   
                   not.showError();
        }
    
    }


   
}
