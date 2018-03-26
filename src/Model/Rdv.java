/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Heythem
 */
public class Rdv {

    private int id;
    private String etat;
    private String date;
    private String heure_debut;
    private String heure_fin;
    private boolean notif;
    private boolean notifSMS;
    private int user_id;
    private int calendrier_id;

    /*public Rdv(int id, String etat, String date, String heure_debut, String heure_fin, int user_id) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.user_id = user_id;
    }*/
    public Rdv(int id, String etat, String date, String heure_debut, String heure_fin, int user_id, int calendrier_id) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.notif = false;
        this.notifSMS = false;
        this.user_id = user_id;
        this.calendrier_id = calendrier_id;
    }

    public Rdv(String etat, String date, String heure_debut, String heure_fin, int user_id, int calendrier_id) {
        this.etat = etat;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.notif = false;
        this.notifSMS = false;
        this.user_id = user_id;
        this.calendrier_id = calendrier_id;
    }

    /*public Rdv(int id, String etat, String date, String heure_debut, String heure_fin) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.notif = false;
        this.notifSMS = false;
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }

    public boolean isNotifSMS() {
        return notifSMS;
    }

    public void setNotifSMS(boolean notifSMS) {
        this.notifSMS = notifSMS;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCalendrier_id() {
        return calendrier_id;
    }

    public void setCalendrier_id(int calendrier_id) {
        this.calendrier_id = calendrier_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "Rdv{" + "id=" + id + ", etat=" + etat + ", date=" + date + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", notif=" + notif + ", notifSMS=" + notifSMS + ", user_id=" + user_id + ", calendrier_id=" + calendrier_id + '}';
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
        final Rdv other = (Rdv) obj;
        return this.id == other.id;
    }

}
