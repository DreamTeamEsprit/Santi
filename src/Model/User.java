/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DELL
 */
public class User {

    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private boolean enabled;
    private String roles;
    private String password;
    private String avatar;
    private String nom;
    private String prenom;
    private String numTel;
    private String adresse;
    private String ville;

    public User() {
    }

    /*public User(String email, String nom, String numTel, String adresse) {
        this.email = email;
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
    }*/
    public User(int id, String username, String username_canonical, String email, boolean enabled, String roles, String password, String avatar, String nom, String prenom, String numTel) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.password = password;
        this.avatar = avatar;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
    }

    public User(String username, String nom, String ville, String numTel, String adresse) {
        this.username = username;
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.ville = ville;
    }
/*
    public User(String username, String nom, String ville) {
        this.username = username;
        this.nom = nom;
        this.ville = ville;
    }

    public User(int id, String nom, String ville) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
    }*/
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public User(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", enabled=" + enabled + ", roles=" + roles + ", password=" + password + ", avatar=" + avatar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
