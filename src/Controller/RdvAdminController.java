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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Rdv;
import Service.RdvService;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class RdvAdminController implements Initializable {

    private RdvService SRdv = new RdvService();

    @FXML
    private TableColumn<Rdv, String> columnDate;
    @FXML
    private TableColumn<Rdv, String> columnHeureDebut;
    @FXML
    private TableColumn<Rdv, String> columnHeureFin;
    @FXML
    private TableColumn<Rdv, String> columnEtat;
    @FXML
    private TableView<Rdv> tableRdv;
    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            columnHeureDebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
            columnHeureFin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
            columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

            List<Rdv> list = SRdv.getAll();
            ObservableList<Rdv> items = FXCollections.observableArrayList(list);

            tableRdv.setItems(items);

            PieChart.setData(SRdv.RdvByService());
            PieChart.setStartAngle(90);
        } catch (Exception ex) {
            Logger.getLogger(RdvAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
