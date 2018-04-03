/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author marwen b-al
 */
public class panier {
    
    int id ;
    int idproduit ;
    int quantite ;
    String nomproduit ;
    float prixproduit ;
    float soustotal ;

    public panier() {
    }

    public panier(int id, int idproduit, int quantite, String nomproduit, float prixproduit, float soustotal) {
        this.id = id;
        this.idproduit = idproduit;
        this.quantite = quantite;
        this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
        this.soustotal = soustotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public float getPrixproduit() {
        return prixproduit;
    }

    public void setPrixproduit(float prixproduit) {
        this.prixproduit = prixproduit;
    }

    public float getSoustotal() {
        return soustotal;
    }

    public void setSoustotal(float soustotal) {
        this.soustotal = soustotal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.idproduit;
        hash = 67 * hash + this.quantite;
        hash = 67 * hash + Objects.hashCode(this.nomproduit);
        hash = 67 * hash + Float.floatToIntBits(this.prixproduit);
        hash = 67 * hash + Float.floatToIntBits(this.soustotal);
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
        final panier other = (panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idproduit != other.idproduit) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (Float.floatToIntBits(this.prixproduit) != Float.floatToIntBits(other.prixproduit)) {
            return false;
        }
        if (Float.floatToIntBits(this.soustotal) != Float.floatToIntBits(other.soustotal)) {
            return false;
        }
        return Objects.equals(this.nomproduit, other.nomproduit);
    }

    @Override
    public String toString() {
        return "panier{" + "id=" + id + ", idproduit=" + idproduit + ", quantite=" + quantite + ", nomproduit=" + nomproduit + ", prixproduit=" + prixproduit + ", soustotal=" + soustotal + '}';
    }
    
    

    
    
   
    
    
}
