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
import Interface.IConge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.DataSource;
import Model.Conge;
import Model.TempsTravail;

/**
 *
 * @author Amine
 */
public class CongeService implements IConge {

    Connection connection;

    public CongeService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Conge t) {
        String req = "insert into conge (calendrier_id,dateDebut,dateFin) values (?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getCalendrier_id());
            preparedStatement.setString(2, t.getDateDebut());
            preparedStatement.setString(3, t.getDateFin());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Conge t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer r) {
        String req = "delete from conge where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Conge findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Conge> getAll() {
        List<Conge> CongeAL = new ArrayList<>();
        String req = "select * from conge where calendrier_id=1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Conge conge = new Conge(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                CongeAL.add(conge);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CongeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CongeAL;
    }

}
