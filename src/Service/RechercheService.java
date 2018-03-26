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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Model.Specialiste;
import Model.User;

/**
 *
 * @author Amine
 */
public class RechercheService {

    Connection connection;

    public RechercheService() {
        connection = DataSource.getInsatance().getConnection();
    }

    public List<User> getAll(JFXComboBox ServiceCB, JFXTextField NomTF, JFXTextField VilleTF) {
        PreparedStatement preparedStatement;

        List<User> userAL = new ArrayList<>();
        List<Specialiste> specialisteAL = new ArrayList<>();
        String req = "select u.id,u.nom,u.prenom,u.ville,s.service_id,sr.nom,d.nom,s.longitude ,s.latitude,s.id,s.description from utilisateur u join specialiste s join service sr join departement d where sr.departement_id=d.id and s.service_id=sr.id and u.id=s.user_id ";
        if (NomTF.getText().length() != 0) {
            req += "and (concat(u.nom,' ', u.prenom) like '%" + NomTF.getText() + "%' or concat(u.prenom,' ', u.nom) like '%" + NomTF.getText() + "%')";
        }
        if (VilleTF.getText().length() != 0) {
            req += " and u.ville='" + VilleTF.getText() + "'";
        }
        if (!ServiceCB.getSelectionModel().isEmpty()) {
            try {
                String reqIdService = "select id from service where nom='" + ServiceCB.getValue() + "' ";
                preparedStatement = connection.prepareStatement(reqIdService);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()) {
                    //System.out.println(resultSet1.getInt(1));
                    req += " and s.service_id='" + resultSet1.getInt("id") + "'";
                }
            } catch (SQLException ex) {
                Logger.getLogger(RechercheService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nomPrenom = resultSet.getString(2) + " " + resultSet.getString(3);
                String departementService = resultSet.getString(7) + " / " + resultSet.getString(6);
                User user = new User(departementService, nomPrenom, resultSet.getString(4), resultSet.getString(8), resultSet.getString(9));
                userAL.add(user);
                //Specialiste specialiste = new Specialiste(resultSet.getInt(10), resultSet.getString(11), resultSet.getDouble(8), resultSet.getDouble(9));

                //specialisteAL.add(specialiste);
                //userAL.add((User) specialisteAL);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RechercheService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userAL;
    }

    public void combo(JFXComboBox combo) {
        String req = "select id,nom from service";
        PreparedStatement preparedStatement;
        combo.getItems().add("");
        try {

            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                combo.getItems().add(resultSet.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RechercheService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
