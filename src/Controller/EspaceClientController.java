/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import Model.Rdv;
import Model.User;
import Service.CalendrierService;
import Service.CongeService;
import Service.RdvService;
import Service.TempsTravailService;
import Service.UserService;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class EspaceClientController implements Initializable {

    private RdvService sRdv = new RdvService();
    private CalendrierService SCalendrier = new CalendrierService();
    private TempsTravailService sTempsTravail = new TempsTravailService();
    private CongeService sConge = new CongeService();
    @FXML
    private ImageView ImageUser;
    @FXML
    private TableView<Rdv> tableRdv;
    @FXML
    private TableColumn<Rdv, String> ColumnNomSpecialisteRdv;
    @FXML
    private TableColumn<Rdv, String> ColumnDateRdv;
    @FXML
    private TableColumn<Rdv, String> ColumnHeureRdv;
    @FXML
    private TableColumn<Rdv, String> ColumnService;
    @FXML
    private TableColumn<Rdv, String> ColumnEtat;
    @FXML
    private TableView<Rdv> tableSpecialiste;
    @FXML
    private TableColumn<Rdv, String> ColumnNomSpecialiste;
    @FXML
    private TableColumn<Rdv, String> ColumnDomaineSpecialiste;
    @FXML
    private TableColumn<Rdv, String> ColumnNumTel;
    @FXML
    private TableColumn<Rdv, String> ColumnAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            //Mes Rendez-vous
            ColumnDateRdv.setCellValueFactory(new PropertyValueFactory<>("date"));
            ColumnHeureRdv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    LocalTime HeureDebut = LocalTime.parse(param.getValue().getHeure_debut());
                    LocalTime HeureFin = LocalTime.parse(param.getValue().getHeure_fin());
                    String Heure = HeureDebut + " à " + HeureFin;
                    return new SimpleStringProperty(Heure);
                }
            });

            ColumnNomSpecialisteRdv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getNom() + " " + u.getPrenom());
                }
            });

            ColumnService.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getPassword());
                }
            });
            //ColumnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            ColumnEtat.setCellValueFactory(new PropertyValueFactory<Rdv, String>("etat"));
            // Table cell coloring
            ColumnEtat.setCellFactory(new Callback<TableColumn<Rdv, String>, TableCell<Rdv, String>>() {
                public TableCell<Rdv, String> call(TableColumn<Rdv, String> param) {
                    return new TableCell<Rdv, String>() {

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                // Get fancy and change color based on data
                                if (item.contains("Termine")) {
                                    item = "Terminé";
                                    this.setTextFill(Color.GREEN);

                                } else if (item.contains("Cours")) {
                                    item = "En Cours";
                                    this.setTextFill(Color.ORANGE);
                                } else {
                                    item = "Annulé";
                                    this.setTextFill(Color.RED);
                                }
                                setText(item);
                            }
                        }
                    };

                }
            });
            List<Rdv> list = sRdv.getAllRdvSpecialiste();
            ObservableList<Rdv> items = FXCollections.observableArrayList(list);
            tableRdv.setItems(items);

            //Mes specialistes
            ColumnNomSpecialiste.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getNom() + " " + u.getPrenom());
                }
            });

            ColumnDomaineSpecialiste.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getPassword());
                }
            });
            ColumnNumTel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getNumTel());
                }
            });

            ColumnAdresse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rdv, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rdv, String> param) {
                    User u = new User();
                    UserService us = new UserService(u);

                    u = us.selectUserByCalendrier_id(param.getValue().getCalendrier_id());
                    return new SimpleStringProperty(u.getAdresse());
                }
            });
            List<Rdv> listUser = sRdv.getRdvSpecialiste();
            ObservableList<Rdv> itemsUser = FXCollections.observableArrayList(listUser);
            tableSpecialiste.setItems(itemsUser);

        } catch (Exception ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
