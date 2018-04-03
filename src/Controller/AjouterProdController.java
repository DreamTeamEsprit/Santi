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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.CategorieService;
import service.FileUploader;
import service.produitService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class AjouterProdController implements Initializable {

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
    private Button ajouter;
    @FXML
    private Button photo;
    @FXML
    private Label fileimage;
        public ObservableList<String>data=FXCollections.observableArrayList();
    @FXML
    private ImageView imagexxx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException, SQLException {
           
             float rem = Float.parseFloat(remise.getText()); 
        if(catgorie.getValue() == null )
        {
            catgorie.setStyle("-fx-text-inner-color: red");
            catgorie.setStyle("-fx-prompt-text-fill: red");
            catgorie.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            
            alert.setTitle("Attention");
            alert.setContentText("Veuillez Saisir une categorie du produit!");
            alert.showAndWait();
            catgorie.setCursor(Cursor.WAIT);
            
            
        }
        else if(libelle.getText().length()==0)
        {
            
            libelle.setStyle("-fx-text-inner-color: red");
            libelle.setStyle("-fx-prompt-text-fill: red");
            libelle.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir la libelle du produit !");
            alert.showAndWait();
            libelle.setCursor(Cursor.WAIT);
            
            
            
        }
        else if(description.getText().length()==0)
        {
            description.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une description du produit !");
            alert.showAndWait();
            
            
        }
        else if(prix.getText().length()==0  )
        {
            prix.setStyle("-fx-text-inner-color: red");
            prix.setStyle("-fx-prompt-text-fill: red");
            prix.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir le prix du produit");
            prix.setCursor(Cursor.WAIT);
            
            
        }
        else if(remise.getText().length()==0)
        {
            remise.setStyle("-fx-inner-color: red");
            remise.setStyle("-fx-prompt-text-fill: red");
            remise.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une remise");
            alert.showAndWait();
            remise.setCursor(Cursor.WAIT);
            
            
        }
      
         else if(rem > 100 )
        {
            remise.setStyle("-fx-inner-color: red");
            remise.setStyle("-fx-prompt-text-fill: red");
            remise.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Le remise ne doit pas etre superier a 100");
            alert.showAndWait();
            remise.setCursor(Cursor.WAIT);
            
            
        }
        else if(quantiteDispo.getText().length()==0)
            
        {
            quantiteDispo.setStyle("-fx-inner-color: red");
            quantiteDispo.setStyle("-fx-prompt-text-fill: red");
            quantiteDispo.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une quantite disponible");
            alert.showAndWait();
            quantiteDispo.setCursor(Cursor.WAIT);
            
            
        }
                        else if(prixLivraison.getText().length()==0)
        {
            prixLivraison.setStyle("-fx-inner-color: red");
            prixLivraison.setStyle("-fx-prompt-text-fill: red");
            prixLivraison.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir prix de livraison !");
            alert.showAndWait();
            prixLivraison.setCursor(Cursor.WAIT);
            
            
        }
                                else if(marque.getText().length()==0)
        {
            marque.setStyle("-fx-inner-color: red");
            marque.setStyle("-fx-prompt-text-fill: red");
            marque.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir marque");
            alert.showAndWait();
            marque.setCursor(Cursor.WAIT);
            
            
        }
                                        else if(photo.getText().length()==0)
        {
            photo.setStyle("-fx-inner-color: red");
            photo.setStyle("-fx-prompt-text-fill: red");
            photo.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une photo");
            alert.showAndWait();
           ;
            
            
        }
                                       
     else if(dateFabrication.getValue()== null)
        {
            dateFabrication.setStyle("-fx-inner-color: red");
            dateFabrication.setStyle("-fx-prompt-text-fill: red");
            dateFabrication.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir une date de fabrication");
            alert.showAndWait();
            dateFabrication.setCursor(Cursor.WAIT);
            
            
        }

        else
        {
   
            
           LocalDate dateFabricationA = dateFabrication.getValue();

       //  Date dateFabricationAA =  java.sql.Date.valueOf( dateFabricationA );

        String categorieA = (String) catgorie.getValue();
        service.CategorieService csx = new CategorieService();
        categorie cat = csx.rechercherparlibelle(categorieA);
        Integer categor = cat.getId();
        String libelleA = libelle.getText();
        String descriptionA = description.getText();
        String prixA = prix.getText();
        String remiseA = remise.getText();
        String quantiteDispoA = quantiteDispo.getText();
        String prixLivraisonA = prixLivraison.getText();
        String marqueA = marque.getText();
        String photoA = fileimage.getText();
         String dateFabricationAA = dateFabricationA.toString();


        Float prixAA = Float.parseFloat(prixA);
        Float remiseAA = Float.parseFloat(remiseA);
        int quantiteDispoAA = Integer.parseInt(quantiteDispoA);
        Float prixLivraisonAA = Float.parseFloat(prixLivraisonA);
       //  photoA = FileUploader.upload(photoA);
                // e.setPhoto();
            //photoA = ""+photoA;
      
         //   photoA = "http://localhost:8181/images/"+photoA;
            produit prod = new produit();
            
            prod.setCategorie(categor.toString());
            prod.setLibelle(libelleA);
            prod.setDescription(descriptionA);
            prod.setPrix(prixAA);
            prod.setRemise(remiseAA);
            prod.setQuantiteDispo(quantiteDispoAA);
            prod.setPrixLivraison(prixLivraisonAA);
            prod.setMarque(marqueA);
            prod.setPhoto(photoA);
            prod.setDateFabrication(dateFabricationAA);
            
            service.produitService bps = new produitService();
            bps.ajouterProduit(prod);
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Produit ajouté avec succes!");
            alert.showAndWait();
            
            Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/prodAdmin.fxml"));
            Parent root = loader.load();
            

            Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/prodadmin.css").toExternalForm());
            stageS.show();
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

    @FXML
    private void imageDragOver(DragEvent de) {
        Dragboard board = de.getDragboard();
      if (board.hasFiles()) {
        de.acceptTransferModes(TransferMode.ANY);
      }
    }

    @FXML
    private void imageDropped(DragEvent de) throws IOException {
        try {
        Dragboard board = de.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
          fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
        imagexxx.setImage(image);
        /*File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
           
           Upload u = new Upload();
           u.upload(selectedFile);
           imageBP1.setText(selectedFile.getName());*/
        File selectedFile = phil.get(0);
        if (selectedFile != null) {
           
           Upload u = new Upload();
           u.upload(selectedFile);
           fileimage.setText(selectedFile.getName());
            
                  
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    
    
}
