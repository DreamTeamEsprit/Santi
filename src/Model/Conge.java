/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Heythem
 */
public class Conge {

    private int id;
    private int calendrier_id;
    private String dateDebut;
    private String dateFin;

    public Conge() {
    }

    public Conge(int id, int calendrier_id, String dateDebut, String dateFin) {
        this.id = id;
        this.calendrier_id = calendrier_id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Conge(int calendrier_id, String dateDebut, String dateFin) {
        this.calendrier_id = calendrier_id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Conge{" + "id=" + id + ", calendrier_id=" + calendrier_id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Conge other = (Conge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
