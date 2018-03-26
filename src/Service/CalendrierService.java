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
import Interface.ICalendrier;
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
import Model.Calendrier;
import Model.User;

/**
 *
 * @author Amine
 */
public class CalendrierService implements ICalendrier {

    Connection connection;
    private ObservableList data;

    public CalendrierService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void update(Calendrier t) {

        String req = "update calendrier set duree=? where id = ?";
        PreparedStatement preparedStatement;
        System.out.println(t.getDuree());
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getDuree());
            preparedStatement.setInt(2, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalendrierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendrier findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Calendrier> getAll() {
        List<Calendrier> calendrierAL = new ArrayList<>();
        String req = "select * from calendrier where id=1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Calendrier calendrier = new Calendrier(resultSet.getInt("id"), resultSet.getInt("specialiste_id"), resultSet.getInt("duree"));
                calendrierAL.add(calendrier);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CalendrierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calendrierAL;
    }

    @Override
    public void add(Calendrier t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getDuree(Integer idCalendrier) {
        Calendrier c = new Calendrier();
        String req = "select duree from calendrier where id =" + idCalendrier + "";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                c.setDuree(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c.getDuree();
    }
}
