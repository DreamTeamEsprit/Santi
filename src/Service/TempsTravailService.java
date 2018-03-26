/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author user
 */
import static GUI.strtotime.strtotime;
import Interface.ITempsTravail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import Model.DataSource;
import Model.Heure;
import Model.TempsTravail;

/**
 *
 * @author Amine
 */
public class TempsTravailService implements ITempsTravail {

    Connection connection;

    public TempsTravailService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(TempsTravail t) {
        String req = "insert into temps_travail (calendrier_id,jour,heureDebut,heureFin) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getCalendrier_id());
            preparedStatement.setString(2, t.getJour());
            preparedStatement.setString(3, t.getHeureDebut());
            preparedStatement.setString(4, t.getHeureFin());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(TempsTravail t) {
    }

    @Override
    public void remove(Integer r) {
        String req = "delete from temps_travail where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public TempsTravail findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempsTravail> getAll() {
        List<TempsTravail> TempsTravailAL = new ArrayList<>();
        String req = "select id,calendrier_id,jour,DATE_FORMAT(heureDebut,'%H:%i') as heureDebut,DATE_FORMAT(heureFin,'%H:%i') as heureFin from temps_travail where calendrier_id=1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                TempsTravail tempsTravail = new TempsTravail(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                TempsTravailAL.add(tempsTravail);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TempsTravailAL;
    }

    public List<TempsTravail> getCalendrier() {
        List<TempsTravail> TempsTravailAL = new ArrayList<>();
        String req = "select id,calendrier_id,jour,DATE_FORMAT(heureDebut,'%H:%i') as heureDebut,DATE_FORMAT(heureFin,'%H:%i') as heureFin from temps_travail where and calendrier_id=1";
        String req1 = "select duree from calendrier where id=1";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalTime debut_temps = LocalTime.parse(resultSet.getString(4));
                LocalTime fin_temps = LocalTime.parse(resultSet.getString(5));
                for (LocalTime j = debut_temps; j.isBefore(fin_temps); j = j.plusMinutes(60)) {

                    //String heureDebutFin=resultSet.getString(4)++ resultSet.getString(5);
                    TempsTravail tempsTravail = new TempsTravail(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), j.toString(), resultSet.getString(5));
                    TempsTravailAL.add(tempsTravail);
                }
                preparedStatement = connection.prepareStatement(req1);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                int duree = 0;
                while (resultSet1.next()) {
                    // int hd = Integer.parseInt(resultSet.getString(4)) + Integer.parseInt(resultSet.getString(1));
                    //  System.out.println(hd);

                    duree = Integer.parseInt(resultSet1.getString(1)) * 60;
                }
                /*
                //charger temps de travail
                //int nb = resultSet.getInt(0);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM temps_travail where calendrier_id=1");
                // get the number of rows from the result set
                rs.next();
                int rowCount = rs.getInt(1);

                System.out.println(rowCount);
                for (int i = 0; i < rowCount; i++) {
                    //Date debut_temps = strtotime(resultSet.getString(4));
                    //Date fin_temps = strtotime(resultSet.getString(5));

                    Date date = new Date();
                    DateFormat debut_temps = new SimpleDateFormat(resultSet.getString(4));
                    DateFormat fin_temps = new SimpleDateFormat(resultSet.getString(5));
                    System.out.println(debut_temps.format(date));

                    System.out.println(strtotime(resultSet.getString(4)));
                    for (DateFormat j = debut_temps; j < fin_temps; j = duree) {
                        //$calendrier[$tempEmplois[$i]['jour']]['evenement'][$this -> timeTostr($j)] = false;
                        System.out.println("test");
                    }
                }*/

            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TempsTravailAL;
    }

    public List<TempsTravail> getTemps() {
        List<TempsTravail> TempsTravailAL = new ArrayList<>();
        List<LocalTime> TempsTravailTime = new ArrayList<>();
        String req = "select id,calendrier_id,jour,DATE_FORMAT(heureDebut,'%H:%i') as heureDebut,DATE_FORMAT(heureFin,'%H:%i') as heureFin from temps_travail where calendrier_id=1";
        String req1 = "select duree from calendrier where id=1";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //String heureDebutFin=resultSet.getString(4)++ resultSet.getString(5);
                //TempsTravail tempsTravail = new TempsTravail(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

                LocalTime time = LocalTime.parse("08:00");
                TempsTravailTime.add(time);
                TempsTravail tempsTravail = new TempsTravail(TempsTravailTime);
                TempsTravailAL.add(tempsTravail);
                System.out.println(TempsTravailTime);
                preparedStatement = connection.prepareStatement(req1);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                int duree = 0;
                while (resultSet1.next()) {
                    // int hd = Integer.parseInt(resultSet.getString(4)) + Integer.parseInt(resultSet.getString(1));
                    //  System.out.println(hd);

                    duree = Integer.parseInt(resultSet1.getString(1)) * 60;
                }
                /*
                //charger temps de travail
                //int nb = resultSet.getInt(0);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM temps_travail where calendrier_id=1");
                // get the number of rows from the result set
                rs.next();
                int rowCount = rs.getInt(1);

                System.out.println(rowCount);
                for (int i = 0; i < rowCount; i++) {
                    //Date debut_temps = strtotime(resultSet.getString(4));
                    //Date fin_temps = strtotime(resultSet.getString(5));

                    Date date = new Date();
                    DateFormat debut_temps = new SimpleDateFormat(resultSet.getString(4));
                    DateFormat fin_temps = new SimpleDateFormat(resultSet.getString(5));
                    System.out.println(debut_temps.format(date));

                    System.out.println(strtotime(resultSet.getString(4)));
                    for (DateFormat j = debut_temps; j < fin_temps; j = duree) {
                        //$calendrier[$tempEmplois[$i]['jour']]['evenement'][$this -> timeTostr($j)] = false;
                        System.out.println("test");
                    }
                }*/

            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TempsTravailAL;
    }

    public List getHours() {
        List<Heure> TempsTravailAL = new ArrayList<>();

        String req = "select t.id,t.calendrier_id,t.jour,DATE_FORMAT(t.heureDebut,'%H:%i') as heureDebut,DATE_FORMAT(t.heureFin,'%H:%i') as heureFin,c.duree from temps_travail t join calendrier c where c.id=t.calendrier_id and t.calendrier_id=1";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalTime debut_temps = LocalTime.parse(resultSet.getString(4));
                LocalTime fin_temps = LocalTime.parse(resultSet.getString(5));
                Integer duree = resultSet.getInt(6);
                for (LocalTime j = debut_temps; j.isBefore(fin_temps); j = j.plusMinutes(duree)) {
                    Heure h = new Heure();
                    if ("Lundi".equals(resultSet.getString(3))) {
                        h.setLundi(j.toString());
                    }
                    if ("Mardi".equals(resultSet.getString(3))) {
                        h.setMardi(j.toString());
                    }
                    if ("Mercredi".equals(resultSet.getString(3))) {
                        h.setMercredi(j.toString());
                    }
                    if ("Jeudi".equals(resultSet.getString(3))) {
                        h.setJeudi(j.toString());
                    }
                    if ("Vendredi".equals(resultSet.getString(3))) {
                        h.setVendredi(j.toString());
                    }
                    if ("Samedi".equals(resultSet.getString(3))) {
                        h.setSamedi(j.toString());
                    }
                    if ("Dimanche".equals(resultSet.getString(3))) {
                        h.setDimanche(j.toString());
                    }

                    //TempsTravail t = new TempsTravail();
                    //t.setId(resultSet.getInt(1));
                    //t.setHeureDebut(j.toString());
                    TempsTravailAL.add(h);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TempsTravailAL;
    }
}
