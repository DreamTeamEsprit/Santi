/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.panier;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.panierService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class ProduitDetailsController implements Initializable {
    private static int id;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField remise;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField date;
    private ImageView im;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton acheter;
    @FXML
    private ImageView imagex;
    @FXML
    private ImageView ajoutdrag;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        
        
        
        acheter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
    
                    try {
                                             service.produitService ps = new service.produitService();
  
            produit p;
                        p = ps.selectProduit(id);
                          panierService pas = new panierService();
                    pas.ajouterPanier(p);
                    } catch (SQLException ex) {
                        Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    
                     
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Produit ajouté avec succes dans Votre Panier");
            alert.showAndWait();
            
            
                    try {
                        Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/espaceUser.fxml"));
            Parent root;
                        root = loader.load();
                           Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/espaceuser.css").toExternalForm());
            stageS.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            

         
           
                } 
         });

        service.produitService ps = new service.produitService();
        try {
            produit p = ps.selectProduit(id);
            drogimage(p);
            String prixa = String.valueOf(p.getPrix());
            String remisea = String.valueOf(p.getRemise());
            nom.setText(p.getLibelle());
            prix.setText(prixa+"    DT");
            remise.setText(remisea+"    %");
            marque.setText(p.getMarque());
            date.setText(p.getDateFabrication());
            description.setText(p.getDescription());
             //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("C:\\wamp64\\www\\images\\"+p.getPhoto());
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        imagex.setImage(IMAGE_RUBY);
                       
                        
                        
                        
                        
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }    
    
    public void redirect(String ide) {

        System.out.println(id);
        ProduitDetailsController.id = Integer.parseInt(ide);

    }

    private void setGraphic(ImageView imgview) {
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/espaceUser.fxml"));
        Parent root = loader.load();
      
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        scene1.getStylesheets().addAll(this.getClass().getResource("/content/espaceuser.css").toExternalForm());
        stage.show();
    }

    @FXML
    private void acheter(ActionEvent event) {
        
    }
    
    private void drogimage ( produit e )
{
    
    
     
                       imagex.setOnDragDetected((MouseEvent event) -> {
        Dragboard db = imagex.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(imagex.getImage());
        db.setContent(content);
        event.consume();
    });

    imagex.setOnDragOver(new EventHandler<DragEvent>() {
        public void handle(DragEvent event) {
            if (event.getGestureSource() != imagex &&
                    event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            event.consume();  

           
         
            
             }
    });

    imagex.setOnDragDone(new EventHandler<DragEvent>() {
        public void handle(DragEvent event) {

            Dragboard db = event.getDragboard();
 ClipboardContent content = new ClipboardContent();
    content.putImage(imagex.getImage());
           if (db.hasImage()) {
                ajoutdrag.setImage(db.getImage());
            }
             event.consume(); 
       
                         service.panierService xy = new panierService();
                    panier pr = new panier();
            try {
                pr = xy.selectProduitpanier(e.getId());
            } catch (SQLException ex) {
                Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        if(pr.getId() != 0  )
        {
             service.panierService xyy = new panierService();
          
         xyy.modifierPanier(pr, pr.getId());
             
            System.out.println("famamennou");
        }
        else 
        {
       // panier prodrx = new panier();
     /*  prodrx.setIdproduit(p.getId());
        prodrx.setNomproduit(p.getLibelle());
        prodrx.setPrixproduit(p.getPrix());*/
            xy.ajouterPanier(e);
              System.out.println("maafamech mennou");
        }
                     
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Le Produit    "+e.getLibelle()+"      est ajouté avec succes dans Votre Panier");
             alert.showAndWait();
            
            
                    try {
                        Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/espaceUser.fxml"));
            Parent root;
                        root = loader.load();
                           Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/espaceuser.css").toExternalForm());
            stageS.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
    });
    
                       
                     
}

}
