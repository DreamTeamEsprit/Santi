/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.ButtonCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Model.User;
import Service.RechercheService;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.sun.prism.impl.Disposer.Record;
import java.util.Iterator;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class RechercheController implements Initializable, MapComponentInitializedListener {

    private RechercheService sRecherche = new RechercheService();
    @FXML
    private JFXTextField NomTF;
    @FXML
    private JFXTextField VilleTF;
    @FXML
    private JFXComboBox<String> ServiceCB;
    @FXML
    private JFXButton RechercherBtn;
    @FXML
    private TableColumn<User, String> ColumnNom;
    @FXML
    private TableColumn<User, String> ColumnService;
    @FXML
    private TableColumn<User, String> ColumnVille;
    @FXML
    private TableView<User> tableRecherche;
    @FXML
    private GoogleMapView mapView;
    Connection connection;

    private GoogleMap map;
    @FXML
    private JFXButton prendreRdv;

    TableColumn col_action = new TableColumn<>();
    TableColumn image = new TableColumn<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableRecherche.setPrefWidth(605);
        tableRecherche.setPrefHeight(230);
        sRecherche.combo(ServiceCB);
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnService.setCellValueFactory(new PropertyValueFactory<>("username"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<>("ville"));

        tableRecherche.getColumns().add(col_action);

        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });
        tableRecherche.getColumns().add(0, image);

        List<User> list = sRecherche.getAll(ServiceCB, NomTF, VilleTF);
        ObservableList<User> items = FXCollections.observableArrayList(list);
        tableRecherche.setItems(items);
        mapView.addMapInializedListener(this);
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnService.setCellValueFactory(new PropertyValueFactory<>("username"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        });
        List<User> list = sRecherche.getAll(ServiceCB, NomTF, VilleTF);
        ObservableList<User> items = FXCollections.observableArrayList(list);
        tableRecherche.setItems(items);
        mapInitialized();
    }

    @FXML
    private void ClearCB(MouseEvent event) {
        ServiceCB.getSelectionModel().select("");
    }

    @Override
    public void mapInitialized() {
        List<User> list = sRecherche.getAll(ServiceCB, NomTF, VilleTF);
        Iterator<User> it = list.iterator();
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(35.7675425, 10.8307048))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(7);

        map = mapView.createMap(mapOptions);

        while (it.hasNext()) {
            User s = it.next();
            LatLong latlng = new LatLong(Double.parseDouble(s.getAdresse()), Double.parseDouble(s.getNumTel()));

            //Add markers to the map
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(latlng);
            Marker joeSmithMarker = new Marker(markerOptions1);

            map.addMarker(joeSmithMarker);

            /*InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h2>" + s.getNom()
                    + "</h2>"
                    + "Current Location: " + s.getVille() + "<br>"
            );

            InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
            fredWilkeInfoWindow.open(map, joeSmithMarker);
             */
        }
    }
}
