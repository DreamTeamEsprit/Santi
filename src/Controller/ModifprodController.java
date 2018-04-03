/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXComboBox;
import connexionDatabase.MyDB;
import entities.Upload;
import entities.categorie;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.CategorieService;
import service.produitService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class ModifprodController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private JFXComboBox<String> catgorie;
    @FXML
    private TextField libelle;
    @FXML
    private TextField description;
    @FXML
    private TextField marque;
    @FXML
    private DatePicker dateFabrication;
    @FXML
    private TextField prix;
    @FXML
    private TextField remise;
    @FXML
    private TextField quantiteDispo;
    @FXML
    private TextField prixLivraison;
    @FXML
    private Button photo;
    @FXML
    private Label fileimage;
      private static int id;
    @FXML
    private Button modifier;
        public ObservableList<String>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    service.produitService ps = new service.produitService();
       
            produit p;
        try {
            p = ps.selectProduit(id);
             String prixa = String.valueOf(p.getPrix());
            String remisea = String.valueOf(p.getRemise());
             String qd = String.valueOf(p.getQuantiteDispo());
               String pl = String.valueOf(p.getPrixLivraison());


            MyDB myDB = MyDB.getInstance();
            Statement stm;
        try {
            stm = myDB.getConnexion().createStatement();
             ResultSet rest=stm.executeQuery("select libelle from categorie where 1");
            
            categorie  cat = new categorie();
               while(rest.next())
               {
           data.add(rest.getString(1));

               }
               catgorie.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            libelle.setText(p.getLibelle());
            description.setText(p.getDescription());
            prix.setText(prixa);
            remise.setText(remisea);
            quantiteDispo.setText(qd);
            prixLivraison.setText(pl);
            marque.setText(p.getMarque());
            fileimage.setText(p.getPhoto());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModifprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
  
      
    }    



    @FXML
    private void addImage(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
           
         Upload u = new Upload();
      u.upload(selectedFile);
          // photo.setText(selectedFile.getAbsolutePath());
           fileimage.setText(selectedFile.getName());
           
    }
    }
    
    public void redirect(String ide) {

        System.out.println(id);
        ModifprodController.id = Integer.parseInt(ide);

    }

    @FXML
    private void ModifierProduit(ActionEvent event) throws IOException, SQLException {
          Float prixAA = Float.parseFloat(prix.getText());
        Float remiseAA = Float.parseFloat(remise.getText());
        int quantiteDispoAA = Integer.parseInt(quantiteDispo.getText());
        Float prixLivraisonAA = Float.parseFloat(prixLivraison.getText());
 String categorieA = (String) catgorie.getValue();
        service.CategorieService csx = new CategorieService();
        categorie cat = csx.rechercherparlibelle(categorieA);
        Integer categor = cat.getId();
      LocalDate dateFabricationA = dateFabrication.getValue();
            produit prod = new produit();
            
            prod.setCategorie(categor.toString());
            prod.setLibelle(libelle.getText());
            prod.setDescription(description.getText());
            
            prod.setPrix(prixAA);
            prod.setRemise(remiseAA);
            prod.setQuantiteDispo(quantiteDispoAA);
            prod.setPrixLivraison(prixLivraisonAA);
            prod.setMarque(marque.getText());
            prod.setPhoto(fileimage.getText());
         
           if (dateFabricationA == null)
           {
               service.produitService ps = new service.produitService();
       
            produit p;
 
            p = ps.selectProduit(id);
            String date = p.getDateFabrication();
            
                         prod.setDateFabrication(date);

           }
           else {
          String dateFabricationAA = dateFabricationA.toString();
          prod.setDateFabrication(dateFabricationAA);
           }
          
            service.produitService bps = new produitService();
            bps.modifierProduit(prod, id);
          
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Produit modifé avec succes!");
            alert.showAndWait();
            
            Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/prodAdmin.fxml"));
            Parent root = loader.load();
            

            Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/prodadmin.css").toExternalForm());
            stageS.show();
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/prodAdmin.fxml"));
        Parent root = loader.load();
        root.setId("pane");
        
        Scene scene1 = new Scene(root );
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/prodadmin.css").toExternalForm());
        
        stage.show();
    }
}

