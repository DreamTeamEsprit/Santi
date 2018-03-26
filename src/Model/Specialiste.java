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
public class Specialiste {

    private int id;
    private int user_id;
    private int service_id;
    private String description;
    private double longitude;
    private double latitude;

    public Specialiste() {
    }

    public Specialiste(int id, String description, double longitude, double latitude) {
        this.id = id;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Specialiste(int id, int user_id, int service_id, String description, double longitude, double latitude) {
        this.id = id;
        this.user_id = user_id;
        this.service_id = service_id;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Specialiste{" + "id=" + id + ", user_id=" + user_id + ", service_id=" + service_id + ", description=" + description + ", longitude=" + longitude + ", latitude=" + latitude + '}';
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
        final Specialiste other = (Specialiste) obj;
        return this.id == other.id;
    }

}
