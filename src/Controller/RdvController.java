/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Rdv;
import Service.RdvService;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class RdvController implements Initializable {

    private RdvService SRdv = new RdvService();
    // private Rdv rdv = new Rdv();

    @FXML
    private TableView<Rdv> tableRdv;
    @FXML
    private TableColumn<Rdv, Integer> columnId;
    @FXML
    private TableColumn<Rdv, String> columnEtat;
    @FXML
    private TableColumn<Rdv, String> columnDate;
    @FXML
    private TableColumn<Rdv, String> columnHeureD;
    @FXML
    private TableColumn<Rdv, String> columnHeureF;

    String idnew;
    @FXML
    private TextField heureFTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField etatTF;
    @FXML
    private TextField heureDTF;
    @FXML
    private TextField userTF;
    @FXML
    private TextField dateTF;
    @FXML
    private TextField specialisteTF;
    @FXML
    private Button AjoutBtn;
    @FXML
    private Button ModifierBtn;
    @FXML
    private Button SupprimerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));//kol coumn lezim naaref mnech bech naffectih kima classe personne
            columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            columnHeureD.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
            columnHeureF.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
            //columnUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            //columnSpecialiste.setCellValueFactory(new PropertyValueFactory<>("id_calendrier"));

            List<Rdv> list = SRdv.getAll();//chargina liste bech n3abeha fi able view

            ObservableList<Rdv> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste

            tableRdv.setItems(items);

            tableRdv.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                showdetails(newValue);
            }));

            // TODO
        } catch (Exception ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showdetails(Rdv r) {
        idnew = Integer.toString(r.getId());

        idTF.setText(Integer.toString(r.getId()));
        etatTF.setText(r.getEtat());
        dateTF.setText(r.getDate());
        heureDTF.setText(r.getHeure_debut());
        heureFTF.setText(r.getHeure_fin());
        // userTF.setText(Integer.toString(r.getId_user()));
        // specialisteTF.setText(Integer.toString(r.getId_calendrier()));

    }

    @FXML
    private void remove(ActionEvent event) {

        try {
            SRdv.remove(tableRdv.getSelectionModel().getSelectedItem().getId());
            List<Rdv> list = SRdv.getAll();//chargina liste bech n3abeha fi able view

            ObservableList<Rdv> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste

            tableRdv.setItems(items);
        } catch (Exception ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update(ActionEvent event) throws Exception {

        int id = Integer.parseInt(idTF.getText());
        String etat = etatTF.getText();
        String date = dateTF.getText();
        String heureDebut = heureDTF.getText();
        String heureFin = heureFTF.getText();

        Rdv rdv = new Rdv(id, etat, date, heureDebut, heureFin,1,1);
        rdv.setId(Integer.parseInt(idnew));
        SRdv.update(rdv);
        List<Rdv> list = SRdv.getAll();//chargina liste bech n3abeha fi able view 

        ObservableList<Rdv> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste 

        tableRdv.setItems(items);

        idTF.setText("");
        etatTF.setText("");
        dateTF.setText("");
        heureDTF.setText("");
        heureFTF.setText("");
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            Rdv rdv = new Rdv(Integer.parseInt(idTF.getText()), etatTF.getText(), dateTF.getText(), heureDTF.getText(), heureFTF.getText(),1,1);

            SRdv.add(rdv);

            List<Rdv> list = SRdv.getAll();//chargina liste bech n3abeha fi able view

            ObservableList<Rdv> items = FXCollections.observableArrayList(list);//bech n3abiwha baaed fi tabview //bech nhot fih les objets taa liste

            tableRdv.setItems(items);
        } catch (NumberFormatException ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }

        idTF.setText("");
        etatTF.setText("");
        dateTF.setText("");
        heureDTF.setText("");
        heureFTF.setText("");
    }
}
