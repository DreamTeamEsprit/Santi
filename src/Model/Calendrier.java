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
public class Calendrier {

    private int id;
    private int specialiste_id;
    private int duree;

    public Calendrier(int id, int duree) {
        this.id = id;
        this.duree = duree;
    }

    public Calendrier(int id, int specialiste_id, int duree) {
        this.id = id;
        this.specialiste_id = specialiste_id;
        this.duree = duree;
    }

    public Calendrier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecialiste_id() {
        return specialiste_id;
    }

    public void setSpecialiste_id(int specialiste_id) {
        this.specialiste_id = specialiste_id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Calendrier{" + "id=" + id + ", specialiste_id=" + specialiste_id + ", duree=" + duree + '}';
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
        final Calendrier other = (Calendrier) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
