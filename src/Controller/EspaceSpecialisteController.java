/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import Model.Calendrier;
import Model.Conge;
import Model.Rdv;
import Model.TempsTravail;
import Model.User;
import Service.CalendrierService;
import Service.CongeService;
import Service.RdvService;
import Service.TempsTravailService;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class EspaceSpecialisteController implements Initializable {

    private RdvService sRdv = new RdvService();
    private CalendrierService SCalendrier = new CalendrierService();
    private TempsTravailService sTempsTravail = new TempsTravailService();
    private CongeService sConge = new CongeService();
    //private Facture car = new Facture();
    @FXML
    private ImageView image2;
    @FXML
    private TableView<Rdv> tableRdv;
    @FXML
    private TableColumn<Rdv, String> ColumnNomClient;
    @FXML
    private TableColumn<Rdv, String> ColumnDate;
    @FXML
    private TableColumn<Rdv, String> ColumnHeure;
    @FXML
    private TableColumn<Rdv, String> ColumnEtat;
    @FXML
    private TableColumn<Rdv, String> ColummnEdit;
    @FXML
    private Tab categorie;
    @FXML
    private TableView<Rdv> tableUser;
    @FXML
    private TableColumn<Rdv, String> ColumnEmail;
    @FXML
    private TableColumn<Rdv, String> ColumnNom;
    @FXML
    private TableColumn<Rdv, String> ColumnNumTel;
    @FXML
    private TableColumn<Rdv, String> ColumnAdresse;
    @FXML
    private JFXComboBox<Integer> DureeCB;
    @FXML
    private JFXButton ValiderDuree;
    @FXML
    private Label DureeLabel;
    @FXML
    private JFXComboBox<String> JourCB;
    @FXML
    private JFXComboBox<String> HeureDebutCB;
    @FXML
    private JFXComboBox<String> HeureFinCB;
    @FXML
    private JFXButton AjouterTempsTravil;
    @FXML
    private TableColumn<TempsTravail, String> ColumnJour;
    @FXML
    private TableColumn<TempsTravail, String> ColumnHeureDebut;
    @FXML
    private TableColumn<TempsTravail, String> ColumnHeureFin;
    @FXML
    private TableView<TempsTravail> tableTempsTravail;
    @FXML
    private JFXButton SupprimerTempsTravail;
    @FXML
    private JFXButton AjouterConge;
    @FXML
    private TableView<Conge> tableConge;
    @FXML
    private TableColumn<Conge, String> ColumnDateDebut;
    @FXML
    private TableColumn<Conge, String> ColumnDateFin;
    @FXML
    private JFXButton SupprimerConge;
    @FXML
    private DatePicker DateDebutTP;
    @FXML
    private DatePicker DateFinTP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            //Mes Rendez-vous
            ColumnNomClient.setCellValueFactory(new PropertyValueFactory<>("user_id"));//kol coumn lezim naaref mnech bech naffectih kima classe personne
            ColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ColumnHeure.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
            ColumnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            ColummnEdit.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            List<Rdv> list = sRdv.getAllRdv();//chargina liste bech n3abeha fi table view
            ObservableList<Rdv> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste
            tableRdv.setItems(items);

            //Mes clients
            ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));//kol coumn lezim naaref mnech bech naffectih kima classe personne
            ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ColumnNumTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
            ColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            List<Rdv> listUser = sRdv.getRdvUsers();//chargina liste bech n3abeha fi table view
            ObservableList<Rdv> itemsUser = FXCollections.observableArrayList(listUser);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste
            tableUser.setItems(itemsUser);

            //Temps de travail
            ColumnJour.setCellValueFactory(new PropertyValueFactory<>("jour"));
            ColumnHeureDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            ColumnHeureFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            List<TempsTravail> listTempsTravail = sTempsTravail.getAll();
            ObservableList<TempsTravail> itemsTempsTravail = FXCollections.observableArrayList(listTempsTravail);
            tableTempsTravail.setItems(itemsTempsTravail);

            //Conge
            ColumnDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            ColumnDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            List<Conge> listConge = sConge.getAll();
            ObservableList<Conge> itemsConge = FXCollections.observableArrayList(listConge);
            tableConge.setItems(itemsConge);

            List<Calendrier> listCalendrier = SCalendrier.getAll();
            DureeLabel.setText("La durée des rendez-vous est " + listCalendrier.get(0).getDuree() + " minutes");

            //ComboBox Duree
            DureeCB.getItems().add(15);
            DureeCB.getItems().add(30);
            DureeCB.getItems().add(45);
            DureeCB.getItems().add(60);

            //ComboBox Jour Temps de travail
            JourCB.getItems().add("Lundi");
            JourCB.getItems().add("Mardi");
            JourCB.getItems().add("Mercredi");
            JourCB.getItems().add("Jeudi");
            JourCB.getItems().add("Vendredi");
            JourCB.getItems().add("Samedi");
            JourCB.getItems().add("Dimanche");

            //ComboBox Heure debut Temps de travail
            HeureDebutCB.getItems().add("01:00:00");
            HeureDebutCB.getItems().add("02:00:00");
            HeureDebutCB.getItems().add("03:00:00");
            HeureDebutCB.getItems().add("04:00:00");
            HeureDebutCB.getItems().add("05:00:00");
            HeureDebutCB.getItems().add("06:00:00");
            HeureDebutCB.getItems().add("07:00:00");
            HeureDebutCB.getItems().add("08:00:00");
            HeureDebutCB.getItems().add("09:00:00");
            HeureDebutCB.getItems().add("10:00:00");
            HeureDebutCB.getItems().add("11:00:00");
            HeureDebutCB.getItems().add("12:00:00");
            HeureDebutCB.getItems().add("13:00:00");
            HeureDebutCB.getItems().add("14:00:00");
            HeureDebutCB.getItems().add("15:00:00");
            HeureDebutCB.getItems().add("16:00:00");
            HeureDebutCB.getItems().add("17:00:00");
            HeureDebutCB.getItems().add("18:00:00");
            HeureDebutCB.getItems().add("19:00:00");
            HeureDebutCB.getItems().add("20:00:00");
            HeureDebutCB.getItems().add("21:00:00");
            HeureDebutCB.getItems().add("22:00:00");
            HeureDebutCB.getItems().add("23:00:00");
            HeureDebutCB.getItems().add("00:00:00");

            //ComboBox Heure fin Temps de travail
            HeureFinCB.getItems().add("01:00:00");
            HeureFinCB.getItems().add("02:00:00");
            HeureFinCB.getItems().add("03:00:00");
            HeureFinCB.getItems().add("04:00:00");
            HeureFinCB.getItems().add("05:00:00");
            HeureFinCB.getItems().add("07:00:00");
            HeureFinCB.getItems().add("08:00:00");
            HeureFinCB.getItems().add("09:00:00");
            HeureFinCB.getItems().add("10:00:00");
            HeureFinCB.getItems().add("11:00:00");
            HeureFinCB.getItems().add("12:00:00");
            HeureFinCB.getItems().add("13:00:00");
            HeureFinCB.getItems().add("14:00:00");
            HeureFinCB.getItems().add("15:00:00");
            HeureFinCB.getItems().add("16:00:00");
            HeureFinCB.getItems().add("17:00:00");
            HeureFinCB.getItems().add("18:00:00");
            HeureFinCB.getItems().add("19:00:00");
            HeureFinCB.getItems().add("20:00:00");
            HeureFinCB.getItems().add("21:00:00");
            HeureFinCB.getItems().add("22:00:00");
            HeureFinCB.getItems().add("23:00:00");
            HeureFinCB.getItems().add("00:00:00");

        } catch (Exception ex) {
            Logger.getLogger(EspaceSpecialisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ValiderDuree(ActionEvent event) {
        Alert alertOk, alertErr;
        alertOk = new Alert(Alert.AlertType.INFORMATION);
        alertErr = new Alert(Alert.AlertType.CONFIRMATION);
        alertErr.setTitle("Confirmer la modification");
        alertErr.setContentText("Modifier?");

        Integer duree = DureeCB.getValue();
        Calendrier calendrier = new Calendrier();
        calendrier.setDuree(duree);
        calendrier.setId(1);
        calendrier.setSpecialiste_id(2);

        SCalendrier.update(calendrier);
        alertOk.setTitle("Etat Mise A jour");
        alertOk.setHeaderText("Modifier Offre Produit");
        alertOk.setContentText("Mise à jour du produit  a été éffectué avec succés");
        alertOk.showAndWait();
        DureeCB.setValue(duree);
        DureeLabel.setText("La durée des rendez-vous est " + duree + " minutes");

    }

    @FXML
    private void AjouterTempsTravil(ActionEvent event) {
        Alert alertOk, alertErr;
        alertOk = new Alert(Alert.AlertType.INFORMATION);
        alertErr = new Alert(Alert.AlertType.CONFIRMATION);
        alertErr.setTitle("Confirmer l'ajout");
        alertErr.setContentText("Ajouter?");
        try {
            TempsTravail tempsTravail = new TempsTravail(1, JourCB.getValue(), HeureDebutCB.getValue(), HeureFinCB.getValue());

            sTempsTravail.add(tempsTravail);

            List<TempsTravail> list = sTempsTravail.getAll();//chargina liste bech n3abeha fi able view

            ObservableList<TempsTravail> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste

            tableTempsTravail.setItems(items);
            alertOk.setTitle("Temps de travail ajouté");
            alertOk.setHeaderText("Ajouter Temps de travail");
            alertOk.setContentText("Ajout Temps de travail a été éffectué avec succés");
            alertOk.showAndWait();
        } catch (NumberFormatException ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerTempsTravail(ActionEvent event) {
        Alert alertOk, alertErr;
        alertOk = new Alert(Alert.AlertType.INFORMATION);
        alertErr = new Alert(Alert.AlertType.CONFIRMATION);
        alertErr.setTitle("Confirmer la suppression");
        alertErr.setContentText("Supprimer?");
        try {
            sTempsTravail.remove(tableTempsTravail.getSelectionModel().getSelectedItem().getId());
            List<TempsTravail> list = sTempsTravail.getAll();//chargina liste bech n3abeha fi able view

            ObservableList<TempsTravail> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste

            tableTempsTravail.setItems(items);
            alertOk.setTitle("Suppression");
            alertOk.setHeaderText("Supprimer temps de travail");
            alertOk.setContentText("Suppression temps de travail a été éffectué avec succés");
            alertOk.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterConge(ActionEvent event) {
        Alert alertOk, alertErr;
        alertOk = new Alert(Alert.AlertType.INFORMATION);
        alertErr = new Alert(Alert.AlertType.CONFIRMATION);
        alertErr.setTitle("Confirmer l'ajout");
        alertErr.setContentText("Ajouter?");
        try {
            Conge conge = new Conge(1, DateDebutTP.getValue().toString(), DateFinTP.getValue().toString());

            sConge.add(conge);

            List<Conge> list = sConge.getAll();

            ObservableList<Conge> items = FXCollections.observableArrayList(list);

            tableConge.setItems(items);
            alertOk.setTitle("Conge ajouté");
            alertOk.setHeaderText("Ajouter Conge");
            alertOk.setContentText("Ajout Conge a été éffectué avec succés");
            alertOk.showAndWait();
        } catch (NumberFormatException ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerConge(ActionEvent event) {
        Alert alertOk, alertErr;
        alertOk = new Alert(Alert.AlertType.INFORMATION);
        alertErr = new Alert(Alert.AlertType.CONFIRMATION);
        alertErr.setTitle("Confirmer la suppression");
        alertErr.setContentText("Supprimer?");
        try {
            sConge.remove(tableConge.getSelectionModel().getSelectedItem().getId());
            List<Conge> list = sConge.getAll();

            ObservableList<Conge> items = FXCollections.observableArrayList(list);

            tableConge.setItems(items);
            alertOk.setTitle("Suppression");
            alertOk.setHeaderText("Supprimer Conge");
            alertOk.setContentText("Suppression Conge a été éffectué avec succés");
            alertOk.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
