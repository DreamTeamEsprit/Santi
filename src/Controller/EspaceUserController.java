/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import com.twilio.http.Request;
import connexionDatabase.MyDB;

import entities.categorie;
import entities.panier;
import entities.prodrechercher;
import entities.produit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionContext;
import org.controlsfx.control.Notifications;
import service.CategorieService;
import service.panierService;
import service.prodrechercherService;
import service.produitService;

/**
 * FXML Controller class
 *
 * @author marwen b-al
 */
public class EspaceUserController implements Initializable {

    @FXML
    private JFXComboBox<String> categorieCB;
    @FXML
    private JFXButton deconnexion;
            
    public ObservableList<String>data=FXCollections.observableArrayList();
    @FXML
    private JFXButton monpanier;

    private int nb = 0 ;
     private static final double MIN_FLICK_PIXELS = 10;
    private static final double FLICK_MOVEMENT = 5;
    @FXML
    private Label labelxxx;
    private enum Direction {
        LEFT, RIGHT
    }
    private double lastXPosition;
    private Direction lastFlickDirection = null;

    TranslateTransition transition = new TranslateTransition();
    
        static produit produit ;

    public ObservableList<produit>data1=FXCollections.observableArrayList();
        public ObservableList<panier>data2=FXCollections.observableArrayList();

    @FXML
    private JFXListView<produit> List;
    service.produitService cs = new produitService();
    List<produit> produitss = cs.selectProduit();
    private JFXTextField textfield;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXTextField recherchepr;
    @FXML
    private JFXButton pluschere;
    @FXML
    private JFXButton moinschere;
    @FXML
    private Pane panex;
    @FXML
    private ImageView imagex;
    @FXML
    private JFXSlider slider;
    @FXML
    private JFXTextField prixtextfield;
    @FXML
    private Label labelxnom;
    @FXML
    private Label labelxprix;
    @FXML
    private Label labelxremise;
    @FXML
    private JFXButton achteterbutton;
    @FXML
    private JFXButton jaimebutton;
    @FXML
    private ImageView imagejaile;
    @FXML
    private ImageView imageacheter;
  
    @FXML
    private Label nbr;
    @FXML
    private JFXButton siteweb;
    @FXML
    private ImageView ajoutdrag;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        nbr.setText(""+nombre());
        
        
        
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
               categorieCB.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
       
               

   ObservableList<produit> items = FXCollections.observableArrayList(produitss);

      
        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
                       
                       
                       
                       
                       
                       
                           
                       
                       
                       
                       if (e != null) {
 

                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                           
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
     List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
     
  
                                  if (
                                        event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                            
                           
 setText("NOM       "+e.getLibelle() +"\n"+" Prix     " + e.getPrix() + " DT"+"\n" + "  Fabriqué En    " + e.getDateFabrication() + "\n "+"  Remise        " + e.getRemise()+ "\n  " + "   Marque      " + e.getMarque()+ "\n"+"\n"+"\n"+"Double Click -> Plus de details ..." );
                           
                           
                          
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                       
                   }
                   
               };
            return cell;
        });

        List.setItems(items);

    
            
    
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
    private void deconnexion(ActionEvent event) throws IOException {
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
    private void monpanier(ActionEvent event) throws IOException {
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
    private void recherchepr(ActionEvent event) throws IOException, SQLException {
        
        
        
             String a =  recherchepr.getText();
      produitService bp = new produitService();
        List<produit> list = bp.getAll( a);
        ObservableList<produit> items = FXCollections.observableArrayList(list);
    if (list.isEmpty())
    {
        service.prodrechercherService xy = new prodrechercherService();
          prodrechercher pr = new prodrechercher();
        pr = xy.selectProduitr(a);
       
        if(pr.getId() != 0  )
        {
             service.prodrechercherService xyy = new prodrechercherService();
          
         xyy.modifierProduit(pr, pr.getId());
             
            System.out.println("famamennou");
        }
        else 
        {
        prodrechercher prodrx = new prodrechercher();
       prodrx.setNom(a);
        
            xy.ajouterProduit(prodrx);
              System.out.println("maaaaaaaaafama mennou");
        }
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Désolé");
            alert.setContentText("Le Produit    "+a+"   n' est pas Disponible");
            alert.showAndWait();
             Notifications not = Notifications.create()
                           .title("Notification")
                           .text("Le produit    "+a+"   Que vous avez chercher sera Disponible Dans Quelque Jours \n N'hesiter pas de consulter notre application")
                           .hideAfter(Duration.seconds(20)) 
                           .onAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
        
                       }
                   });
                         
                   
                   not.showInformation();
            Stage stageS = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/espaceUser.fxml"));
            Parent root = loader.load();
            

            Scene scene1 = new Scene(root);
            stageS.setScene(scene1);
            scene1.getStylesheets().addAll(this.getClass().getResource("/content/espaceuser.css").toExternalForm());
            stageS.show();
        
         
    }
    else
    {
        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
                      
                       if (e != null)
                       {
                           
                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                                  List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
                
                                   if (
                                           event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                           
                           
                           setText(e.getLibelle() + "Prix" + e.getPrix() + "\n" + " Fabriqué En " + e.getDateFabrication() + " Remise " + e.getRemise()+ "\n  " + "    Marque " + e.getMarque()+ "\n" + "                   Prix En DT " + e.getPrix());
                           
                           
                           
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                     
                   }
                   
               };
            return cell;
        });

        List.setItems(items);

    }
        
    }

    @FXML
    private void pluschere(ActionEvent event) {
        
      produitService bp = new produitService();
        List<produit> list = bp.getAllpluschere();
        ObservableList<produit> items = FXCollections.observableArrayList(list);

        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
                      
                       if (e != null)
                       {
                           
                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                               List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
                
                                   if (
                                           event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                           
                           
                           setText(e.getLibelle() + "Prix" + e.getPrix() + "\n" + " Fabriqué En " + e.getDateFabrication() + " Remise " + e.getRemise()+ "\n  " + "    Marque " + e.getMarque()+ "\n" + "                   Prix En DT " + e.getPrix());
                           
                           
                           
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                     
                   }
                   
               };
            return cell;
        });

        List.setItems(items);
    
    }
    @FXML
    private void moinschere(ActionEvent event) {
         produitService bp = new produitService();
        List<produit> list = bp.getAllmoinschere();
        ObservableList<produit> items = FXCollections.observableArrayList(list);

        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
                  
                       if (e != null)
                       {
                           
                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                 
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                                List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
                
                                   if (
                                           event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                           
                           
                           setText(e.getLibelle() + "Prix" + e.getPrix() + "\n" + " Fabriqué En " + e.getDateFabrication() + " Remise " + e.getRemise()+ "\n  " + "    Marque " + e.getMarque()+ "\n" + "                   Prix En DT " + e.getPrix());
                           
                           
                           
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                     
                   }
                   
               };
            return cell;
        });

        List.setItems(items);
    }

    @FXML
    private void rechercheparcategorie(ActionEvent event) throws SQLException {
       
        String cbval = categorieCB.getValue();
        
        service.CategorieService cs = new CategorieService();
        
        categorie cat = cs.rechercherparlibelle(cbval);
        System.out.println(cat);
                  produitService bp = new produitService();
        List<produit> list = bp.getAllparcategorie(cat.getId());
        ObservableList<produit> items = FXCollections.observableArrayList(list);

        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
                 
                       if (e != null)
                       {
                           
                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                         List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
                
                                   if (
                                           event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                           
                           
                           setText(e.getLibelle() + "Prix" + e.getPrix() + "\n" + " Fabriqué En " + e.getDateFabrication() + " Remise " + e.getRemise()+ "\n  " + "    Marque " + e.getMarque()+ "\n" + "                   Prix En DT " + e.getPrix());
                           
                           
                           
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                     
                   }
                   
               };
            return cell;
        });

        List.setItems(items);
             
    }




    @FXML
    private void makePrix(MouseEvent event) {
        Double prix = slider.getValue();
        int prix1 = (int)Math.round(prix);
        prixtextfield.setText(Integer.toString(prix1)+" DT");
         produitService bp = new produitService();
       
          
        List<produit> list = bp.selectProduitPrix(prix);
        ObservableList<produit> items = FXCollections.observableArrayList(list);

        List.setCellFactory((ListView<produit> arg0) -> {
            ListCell<produit> cell;
               cell = new ListCell<produit>() {
                   @Override
                   protected void updateItem(produit e, boolean btl) {
                       super.updateItem(e, btl);
              
                       if (e != null)
                       {
                           
                           //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                           File file = new File("C:\\wamp64\\www\\images\\"+e.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                           
                           ImageView imgview = new ImageView(IMAGE_RUBY);
                           
                           setGraphic(imgview);
                           
                           
                           Circle circle = new Circle(75, 70, 67);
                           circle.setEffect(new DropShadow());
                           
                           
                           
                           
                           imgview.setClip(circle);
                           
                           // snapshot the rounded image.
                           SnapshotParameters parameters = new SnapshotParameters();
                           parameters.setFill(Color.TRANSPARENT);
                           WritableImage image = imgview.snapshot(parameters, null);
                           
                           // remove the rounding clip so that our effect can show through.
                           imgview.setClip(null);
                           
                           // apply a shadow effect.
                           imgview.setEffect(new DropShadow(20, Color.BLACK));
                           
                           // store the rounded image in the imageView.
                           imgview.setImage(image);
                         
                           List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                             List.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
         try {
             showProduitDetails(newValue);
         } catch (IOException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EspaceUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
                
                                   if (
                                           event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                       System.out.println("salut");
                                       produit cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                       ProduitDetailsController l = new ProduitDetailsController();
                                      
                                       l.redirect(String.valueOf(cov.getId()));
                                       AnchorPane pane = new AnchorPane();
                                       try {
                                           pane = FXMLLoader.load(getClass().getResource("/views/produitDetails.fxml"));
                                       } catch (IOException ex) {
                                           Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       rootpane.getChildren().setAll(pane);
                                       
                                   }
                               }
                               
                               
                           });
                           
                           
                           setText(e.getLibelle() + "Prix" + e.getPrix() + "\n" + " Fabriqué En " + e.getDateFabrication() + " Remise " + e.getRemise()+ "\n  " + "    Marque " + e.getMarque()+ "\n" + "                   Prix En DT " + e.getPrix());
                           
                           
                           
                           setFont(Font.font("Berlin Sans FB Demi Bold", 13));
                           
                           // setAlignment(Pos.CENTER);
                       }
                     
                   }
                   
               };
            return cell;
        });

        List.setItems(items);
    }


  
     public void showProduitDetails(produit p)  throws IOException, SQLException {
      
         System.out.println(p);
         if (p != null) {
            panex.setVisible(true);
           File file = new File("C:\\wamp64\\www\\images\\"+p.getPhoto());
                           file.getParentFile().mkdirs();
                           Image IMAGE_RUBY = new Image(file.toURI().toString());
                           imagex.setImage(IMAGE_RUBY);
                           
         labelxnom.setText(p.getLibelle());
         labelxprix.setText(""+p.getPrix()+"    DT");
         labelxremise.setText(""+p.getRemise()+"   %");
         achteterbutton.setText("AJOUTER AU PANIER");
         achteterbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   
                   
                     service.panierService xy = new panierService();
                    panier pr = new panier();
                    try {
                        pr = xy.selectProduitpanier(p.getId());
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
            xy.ajouterPanier(p);
              System.out.println("maafamech mennou");
        }
                     
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.setTitle("Succès");
            alert.setContentText("Le Produit    "+p.getLibelle()+"      est ajouté avec succes dans Votre Panier");
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
         jaimebutton.setText("J aime");
         jaimebutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                }
            });
         Image im = new Image("/content/jaime.jpg");
         imagejaile.setImage(im);
         
                  Image im1 = new Image("/content/customer-testimonials.jpg");
         imageacheter.setImage(im1);
         
        } 
    
     }

    @FXML
    private void siteweb(ActionEvent event) throws URISyntaxException, IOException {
     Desktop.getDesktop().browse(new URI("http://localhost/SanteEtBienEtre1.0/web/app_dev.php"));

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
