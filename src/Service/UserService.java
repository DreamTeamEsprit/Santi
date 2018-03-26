/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.DataSource;

/**
 *
 * @author Radhi
 */
public class UserService {

    Connection connection;

    public UserService(User u) {
        connection = DataSource.getInsatance().getConnection();
    }

    public User selectUser(int id) {
        User u = new User();

        String req = "select * from utilisateur where id =" + id + "";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u.setId(resultSet.getInt(1));
                u.setUsername(resultSet.getString(2));
                u.setEmail(resultSet.getString(4));
                u.setPassword(resultSet.getString(8));
                u.setNom(resultSet.getString(13));
                u.setPrenom(resultSet.getString(14));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public User selectUserByCalendrier_id(int id) {
        User u = new User();

        String req = "select u.nom,u.prenom,sr.nom,u.adresse,u.numTel from utilisateur u join specialiste s join calendrier c join service sr where sr.id=s.service_id and s.id=c.specialiste_id and u.id=s.user_id and c.id =" + id + "";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u.setNom(resultSet.getString(1));
                u.setPrenom(resultSet.getString(2));
                //Nom de Serice
                u.setPassword(resultSet.getString(3));
                u.setAdresse(resultSet.getString(4));
                u.setNumTel(resultSet.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TempsTravailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
