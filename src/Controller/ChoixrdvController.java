/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Model.DataSource;
import Model.Heure;
import Model.Rdv;
import Model.TempsTravail;
import Service.CalendrierService;
import Service.RdvService;
import Service.TempsTravailService;

/**
 * FXML Controller class
 *
 * @author Heythem
 */
public class ChoixrdvController implements Initializable {

    private TempsTravailService sTempsTravail = new TempsTravailService();
    private CalendrierService sCalendrier = new CalendrierService();
    private RdvService sRdv = new RdvService();
    Connection connection = DataSource.getInsatance().getConnection();

    @FXML
    private TableView<Heure> Calendrier;
    int j = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Calendrier.getSelectionModel().setCellSelectionEnabled(true);
        initCalendrier(j);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        Alert alertinf = new Alert(AlertType.INFORMATION);
        ObservableList<TablePosition> selectedCells = Calendrier.getSelectionModel().getSelectedCells();
        selectedCells.addListener((ListChangeListener.Change<? extends TablePosition> change) -> {

            if (selectedCells.size() > 0) {
                TablePosition selectedCell = selectedCells.get(0);
                TableColumn column = selectedCell.getTableColumn();
                int rowIndex = selectedCell.getRow();
                Object data = column.getCellObservableValue(rowIndex).getValue();
                if (data != null) {
                    alert.setTitle("Confirmation Rendez-vous");
                    alert.setHeaderText("Confirmation Rendez-vous");
                    alert.setContentText("Vous êtes sur de prendre ce rendez-vous ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        SubStringEx subEx = new SubStringEx();
                        String dateRdv = subEx.getLastnCharacters(column.getText(), 10);
                        LocalTime heureDebut = LocalTime.parse(data.toString());
                        String heureFin = heureDebut.plusMinutes(sCalendrier.getDuree(1)).toString();
                        Rdv rdv = new Rdv("En Cours", dateRdv, heureDebut.toString(), heureFin, 3, 1);
                        sRdv.add(rdv);
                        alertinf.setTitle("Prise de Rendez-vous");
                        alertinf.setHeaderText(null);
                        alertinf.setContentText("Rendez-vous le " + dateRdv + " à " + heureDebut + " a été pris avec succès!");
                        alertinf.showAndWait();
                    }
                }
            }
        });
    }

    public void initCalendrier(int j) {
        int i = 1;
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();

        TableColumn Lundi = new TableColumn<>("Lundi\n"
                + now.with(fieldISO, i).plusDays(j));
        TableColumn Mardi = new TableColumn<>("     Mardi\n"
                + now.with(fieldISO, i + 1).plusDays(j));
        TableColumn Mercredi = new TableColumn<>("  Mercredi\n"
                + now.with(fieldISO, i + 2).plusDays(j));
        TableColumn Jeudi = new TableColumn<>("     Jeudi\n"
                + now.with(fieldISO, i + 3).plusDays(j));
        TableColumn Vendredi = new TableColumn<>("  Vendredi\n"
                + now.with(fieldISO, i + 4).plusDays(j));
        TableColumn Samedi = new TableColumn<>("   Samedi\n"
                + now.with(fieldISO, i + 5).plusDays(j));
        TableColumn Dimanche = new TableColumn<>(" Dimanche\n"
                + now.with(fieldISO, i + 6).plusDays(j));

        Calendrier.getColumns().add(Lundi);
        Calendrier.getColumns().add(Mardi);
        Calendrier.getColumns().add(Mercredi);
        Calendrier.getColumns().add(Jeudi);
        Calendrier.getColumns().add(Vendredi);
        Calendrier.getColumns().add(Samedi);
        Calendrier.getColumns().add(Dimanche);
        Lundi.setPrefWidth(85.71);
        Mardi.setPrefWidth(85.71);
        Mercredi.setPrefWidth(85.71);
        Jeudi.setPrefWidth(85.71);
        Vendredi.setPrefWidth(85.71);
        Samedi.setPrefWidth(85.71);
        Dimanche.setPrefWidth(85.71);

        Lundi.setCellValueFactory(new PropertyValueFactory<>("Lundi"));
        Mardi.setCellValueFactory(new PropertyValueFactory<>("Mardi"));
        Mercredi.setCellValueFactory(new PropertyValueFactory<>("Mercredi"));
        Jeudi.setCellValueFactory(new PropertyValueFactory<>("Jeudi"));
        Vendredi.setCellValueFactory(new PropertyValueFactory<>("Vendredi"));
        Samedi.setCellValueFactory(new PropertyValueFactory<>("Samedi"));
        Dimanche.setCellValueFactory(new PropertyValueFactory<>("Dimanche"));
        //Lundi.setStyle("-fx-background-color:red");
        List<Heure> list = sTempsTravail.getHours();
        ObservableList<Heure> items = FXCollections.observableArrayList(list);
        Calendrier.setItems(items);
        ChargerRdv();
    }

    @FXML

    private void NextWeek(MouseEvent event
    ) {
        j += 7;
        Calendrier.getItems().clear();
        Calendrier.getColumns().clear();
        initCalendrier(j);
        DisableCalendrier();
        EnableCalendrier();
    }

    @FXML
    private void PreviousWeek(MouseEvent event
    ) {
        j -= 7;
        Calendrier.getItems().clear();
        Calendrier.getColumns().clear();
        initCalendrier(j);
        DisableCalendrier();
        EnableCalendrier();
    }

    private void DisableCalendrier() {
        if (j < 0) {
            Calendrier.setDisable(true);
            Calendrier.getItems().clear();
        }
    }

    private void EnableCalendrier() {
        if (j >= 0) {
            Calendrier.setDisable(false);
        }

    }

    private void ChargerRdv() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate dateDebut = now.with(fieldISO, 1).plusDays(j);
        LocalDate dateFin = now.with(fieldISO, 1).plusDays(j + 7);

        //charger rendez vous
        //List<Rdv> listRdv = sRdv.getAll();
        for (LocalDate d = dateDebut; d.isBefore(dateFin); d = d.plusDays(1)) {
            List<Rdv> listRdv = sRdv.getAll();
            LocalDate ee = d;
            listRdv = listRdv.stream()
                    .filter(e -> ee.equals(LocalDate.parse(e.getDate()))).collect(Collectors.toList());
            for (int k = 0; k < listRdv.stream().count(); k++) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(listRdv.get(k).getDate(), formatter);
                LocalTime debut_temps = LocalTime.parse(listRdv.get(k).getHeure_debut());
                String dateWeek = date.getDayOfWeek().toString();
                if (date.equals(d)) {
                    for (int i = 0; i < Calendrier.getItems().stream().count(); i++) {
                        //Lundi
                        if (Calendrier.getItems().get(i).getLundi() != null && "MONDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getLundi());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setLundi(null);
                            }
                        }
                        //Mardi
                        if (Calendrier.getItems().get(i).getMardi() != null && "TUESDAY".equals(dateWeek)) {
                            LocalTime tempsMar = LocalTime.parse(Calendrier.getItems().get(i).getMardi());
                            if (debut_temps.equals(tempsMar)) {
                                Calendrier.getItems().get(i).setMardi(null);
                            }
                        }
                        //Mercredi
                        if (Calendrier.getItems().get(i).getMercredi() != null && "WEDNESDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getMercredi());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setMercredi(null);
                            }
                        }
                        //Jeudi
                        if (Calendrier.getItems().get(i).getJeudi() != null && "THURSDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getJeudi());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setJeudi(null);
                            }
                        }
                        //Vendredi
                        if (Calendrier.getItems().get(i).getVendredi() != null && "FRIDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getVendredi());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setVendredi(null);
                            }
                        }
                        //Samedi
                        if (Calendrier.getItems().get(i).getSamedi() != null && "SATURDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getSamedi());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setSamedi(null);
                            }
                        }
                        //Dimanche
                        if (Calendrier.getItems().get(i).getDimanche() != null && "SUNDAY".equals(dateWeek)) {
                            LocalTime temps = LocalTime.parse(Calendrier.getItems().get(i).getDimanche());
                            if (debut_temps.equals(temps)) {
                                Calendrier.getItems().get(i).setDimanche(null);
                            }
                        }
                    }
                }
            }

        }
    }

    private static class SubStringEx {

        public String getLastnCharacters(String inputString,
                int subStringLength) {
            int length = inputString.length();
            if (length <= subStringLength) {
                return inputString;
            }
            int startIndex = length - subStringLength;
            return inputString.substring(startIndex);
        }
    }

}
