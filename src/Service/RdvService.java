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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.DataSource;
import Model.Rdv;
import Interface.IRdv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import Model.User;

/**
 *
 * @author Amine
 */
public class RdvService implements IRdv {

    Connection connection;
    private ObservableList data;

    public RdvService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Rdv t) {
        String req = "insert into rdv (etat,date,heure_debut,heure_fin,notif,notifSMS,user_id,calendrier_id) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getEtat());
            preparedStatement.setString(2, t.getDate());
            preparedStatement.setString(3, t.getHeure_debut());
            preparedStatement.setString(4, t.getHeure_fin());
            preparedStatement.setBoolean(5, false);
            preparedStatement.setBoolean(6, false);
            preparedStatement.setInt(7, t.getUser_id());
            preparedStatement.setInt(8, t.getCalendrier_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Rdv t) {
        String req = "update rdv set etat=?,date=?,heure_debut=?,heure_fin=? where id = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getEtat());
            preparedStatement.setString(2, t.getDate());
            preparedStatement.setString(3, t.getHeure_debut());
            preparedStatement.setString(4, t.getHeure_fin());
            preparedStatement.setInt(5, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Integer r) {
        String req = "delete from rdv where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Rdv findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rdv> getAll() {
        List<Rdv> rdvAL = new ArrayList<>();
        String req = "select * from rdv";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String etat = "";

                if ("En Cours".equals(resultSet.getString("etat"))) {
                    etat = "En Cours";
                } else if ("Annule".equals(resultSet.getString("etat"))) {
                    etat = "Annulé";
                } else {
                    etat = "Terminé";
                }
                Rdv rdv = new Rdv(resultSet.getInt("id"), etat, resultSet.getString("date"), resultSet.getString("heure_debut"), resultSet.getString("heure_fin"), resultSet.getInt("user_id"), resultSet.getInt("calendrier_id"));
                rdvAL.add(rdv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rdvAL;
    }

    public List<Rdv> getAllRdv() {
        List<Rdv> rdvAL = new ArrayList<>();
        String req = "select * from rdv where calendrier_id=1";
        //r join utilisateur u where r.user_id = u.id
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String etat = "";

                if ("En Cours".equals(resultSet.getString("etat"))) {
                    etat = "En Cours";
                } else if ("Annule".equals(resultSet.getString("etat"))) {
                    etat = "Annulé";
                } else {
                    etat = "Terminé";
                }
                String heured = resultSet.getString(4);
                String heuref = resultSet.getString(5);
                String nom = resultSet.getString(6) + " " + resultSet.getString(7);

                Rdv rdv = new Rdv(resultSet.getInt("id"), etat, resultSet.getString("date"), resultSet.getString("heure_debut"), resultSet.getString("heure_fin"), resultSet.getInt("user_id"), resultSet.getInt("calendrier_id"));
                rdvAL.add(rdv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rdvAL;
    }

    public List<Rdv> getAllRdvSpecialiste() {
        List<Rdv> rdvAL = new ArrayList<>();
        String req = "select * from rdv where user_id=3";
        //r join utilisateur u where r.user_id = u.id
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Rdv rdv = new Rdv(resultSet.getInt("id"), resultSet.getString("etat"), resultSet.getString("date"), resultSet.getString("heure_debut"), resultSet.getString("heure_fin"), resultSet.getInt("user_id"), resultSet.getInt("calendrier_id"));
                rdvAL.add(rdv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rdvAL;
    }

    public List<Rdv> getRdvUsers() {
        List<Rdv> userAL = new ArrayList<>();
        String req = "select * from rdv where calendrier_id=1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rdv rdv = new Rdv(resultSet.getInt("id"), resultSet.getString("etat"), resultSet.getString("date"), resultSet.getString("heure_debut"), resultSet.getString("heure_fin"), resultSet.getInt("user_id"), resultSet.getInt("calendrier_id"));
                userAL.add(rdv);

            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userAL;
    }

    public List<Rdv> getRdvSpecialiste() {
        List<Rdv> userAL = new ArrayList<>();
        String req = "select * from rdv where user_id = 3 group by calendrier_id";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rdv rdv = new Rdv(resultSet.getInt("id"), resultSet.getString("etat"), resultSet.getString("date"), resultSet.getString("heure_debut"), resultSet.getString("heure_fin"), resultSet.getInt("user_id"), resultSet.getInt("calendrier_id"));
                userAL.add(rdv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userAL;
    }

    public ObservableList<PieChart.Data> RdvByService() {
        data = FXCollections.observableArrayList();
        String req = "select count(r.id) as nombre ,s.nom as label from rdv r join calendrier c join specialiste p join service s where r.calendrier_id = c.id and p.id = c.specialiste_id and p.service_id = s.id group by s.id";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                data.add(new PieChart.Data(resultSet.getString(2), resultSet.getDouble(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RdvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
