package app.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Embeddable
public class Contact implements Serializable {
    private static final long serialVersionUID = -7415410969017941320L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String prenom;

    @NotNull
    private String nom;

    @NotNull
    private String adresse_postale;

    @OneToMany(mappedBy = "contact")
    private Collection<Email> emails;
    protected Contact() {}

    public Contact(String prenom, String nom, String adresse_postale) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse_postale = adresse_postale;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s", id, prenom, nom, adresse_postale, this.emails != null ? emails.toString() : "");
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Collection<Email> getEmails() {
        return this.emails;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse_postale() {
        return adresse_postale;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdressePostale(String adresse) {
        this.adresse_postale = adresse;
    }
}