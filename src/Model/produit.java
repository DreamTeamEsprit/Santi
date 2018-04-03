/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static com.fasterxml.jackson.core.JsonpCharacterEscapes.instance;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;
import main.main;

/**
 *
 * @author marwen b-al
 */
public class produit extends RecursiveTreeObject<produit> {

        private static produit instance;
    public static produit getInstance() {
        
         
                 return instance;

    }
    
     public int id ;
            public String categorie ;
            public int userId ;
            public String libelle ;
            public String description ;
            public float prix ;
            public float remise ;
            public int quantiteDispo ;
            public float prixLivraison ;
            public int quantite ;
            public String marque ;
            public String photo ;
            public String dateFabrication ;

    public produit(int id, String categorie, int userId, String libelle, String description, float prix, float remise, int quantiteDispo, float prixLivraison, int quantite, String marque, String photo, String dateFabrication) {
        this.id = id;
        this.categorie = categorie;
        this.userId = userId;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.remise = remise;
        this.quantiteDispo = quantiteDispo;
        this.prixLivraison = prixLivraison;
        this.quantite = quantite;
        this.marque = marque;
        this.photo = photo;
        this.dateFabrication = dateFabrication;
    }

    public produit() {
    }

    public produit(int id, String categorie, String libelle, String description, float prix, float remise, int quantiteDispo, float prixLivraison, String marque, String photo, String dateFabrication) {
        this.id = id;
        this.categorie = categorie;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.remise = remise;
        this.quantiteDispo = quantiteDispo;
        this.prixLivraison = prixLivraison;
        this.marque = marque;
        this.photo = photo;
        this.dateFabrication = dateFabrication;
    }

    public produit(int id, String categorie, String libelle, int quantiteDispo, String photo) {
        this.id = id;
        this.categorie = categorie;
        this.libelle = libelle;
        this.quantiteDispo = quantiteDispo;
        this.photo = photo;
    }

    public produit(String string) {
    }

    public produit(String libelle, float prix) {
        this.libelle = libelle;
        this.prix = prix;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public int getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(int quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }

    public float getPrixLivraison() {
        return prixLivraison;
    }

    public void setPrixLivraison(float prixLivraison) {
        this.prixLivraison = prixLivraison;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.categorie);
        hash = 73 * hash + this.userId;
        hash = 73 * hash + Objects.hashCode(this.libelle);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Float.floatToIntBits(this.prix);
        hash = 73 * hash + Float.floatToIntBits(this.remise);
        hash = 73 * hash + this.quantiteDispo;
        hash = 73 * hash + Float.floatToIntBits(this.prixLivraison);
        hash = 73 * hash + this.quantite;
        hash = 73 * hash + Objects.hashCode(this.marque);
        hash = 73 * hash + Objects.hashCode(this.photo);
        hash = 73 * hash + Objects.hashCode(this.dateFabrication);
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
        final produit other = (produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (Float.floatToIntBits(this.remise) != Float.floatToIntBits(other.remise)) {
            return false;
        }
        if (this.quantiteDispo != other.quantiteDispo) {
            return false;
        }
        if (Float.floatToIntBits(this.prixLivraison) != Float.floatToIntBits(other.prixLivraison)) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return Objects.equals(this.dateFabrication, other.dateFabrication);
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", categorie=" + categorie + ", userId=" + userId + ", libelle=" + libelle + ", description=" + description + ", prix=" + prix + ", remise=" + remise + ", quantiteDispo=" + quantiteDispo + ", prixLivraison=" + prixLivraison + ", quantite=" + quantite + ", marque=" + marque + ", photo=" + photo + ", dateFabrication=" + dateFabrication + '}';
    }

    public List<produit> displayAllList() {
        return (List<produit>) this ;
    }

    public Object getID() {

        return this ;
    }


 
            
    
}
