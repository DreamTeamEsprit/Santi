/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Heythem
 */
public class TempsTravail {

    private int id;
    private int calendrier_id;
    private SimpleStringProperty jour;
    private String heureDebut;
    private String heureFin;
    private List<LocalTime> TempsTravailAL;

    public TempsTravail(int id, int calendrier_id, String jour, String heureDebut, String heureFin) {
        this.id = id;
        this.calendrier_id = calendrier_id;
        this.jour = new SimpleStringProperty(jour);
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public TempsTravail(int calendrier_id, String jour, String heureDebut, String heureFin) {
        this.calendrier_id = calendrier_id;
        this.jour = new SimpleStringProperty(jour);
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public TempsTravail(List<LocalTime> TempsTravailAL) {
        this.TempsTravailAL = new ArrayList<>();
    }

    public TempsTravail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalendrier_id() {
        return calendrier_id;
    }

    public void setCalendrier_id(int calendrier_id) {
        this.calendrier_id = calendrier_id;
    }

    public String getJour() {
        return jour.get();
    }

    public void setJour(SimpleStringProperty jour) {
        this.jour = jour;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public List<LocalTime> getTempsTravailAL() {
        return TempsTravailAL;
    }

    public void setTempsTravailAL(List<LocalTime> TempsTravailAL) {
        this.TempsTravailAL = TempsTravailAL;
    }

    @Override
    public String toString() {
        return "TempsTravail{" + "id=" + id + ", calendrier_id=" + calendrier_id + ", jour=" + jour + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TempsTravail other = (TempsTravail) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
