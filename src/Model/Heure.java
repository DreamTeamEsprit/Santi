/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Heythem
 */
public class Heure {

    private String Lundi;
    private String Mardi;
    private String Mercredi;
    private String Jeudi;
    private String Vendredi;
    private String Samedi;
    private String Dimanche;

    public Heure() {
    }

    public Heure(String Lundi, String Mardi, String Mercredi, String Jeudi, String Vendredi, String Samedi, String Dimanche) {
        this.Lundi = Lundi;
        this.Mardi = Mardi;
        this.Mercredi = Mercredi;
        this.Jeudi = Jeudi;
        this.Vendredi = Vendredi;
        this.Samedi = Samedi;
        this.Dimanche = Dimanche;
    }

    public String getLundi() {
        return Lundi;
    }

    public void setLundi(String Lundi) {
        this.Lundi = Lundi;
    }

    public String getMardi() {
        return Mardi;
    }

    public void setMardi(String Mardi) {
        this.Mardi = Mardi;
    }

    public String getMercredi() {
        return Mercredi;
    }

    public void setMercredi(String Mercredi) {
        this.Mercredi = Mercredi;
    }

    public String getJeudi() {
        return Jeudi;
    }

    public void setJeudi(String Jeudi) {
        this.Jeudi = Jeudi;
    }

    public String getVendredi() {
        return Vendredi;
    }

    public void setVendredi(String Vendredi) {
        this.Vendredi = Vendredi;
    }

    public String getSamedi() {
        return Samedi;
    }

    public void setSamedi(String Samedi) {
        this.Samedi = Samedi;
    }

    public String getDimanche() {
        return Dimanche;
    }

    public void setDimanche(String Dimanche) {
        this.Dimanche = Dimanche;
    }

    @Override
    public String toString() {
        return "Heure{" + "Lundi=" + Lundi + ", Mardi=" + Mardi + ", Mercredi=" + Mercredi + ", Jeudi=" + Jeudi + ", Vendredi=" + Vendredi + ", Samedi=" + Samedi + ", Dimanche=" + Dimanche + '}';
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
        final Heure other = (Heure) obj;
        if (!Objects.equals(this.Lundi, other.Lundi)) {
            return false;
        }
        return true;
    }

}
