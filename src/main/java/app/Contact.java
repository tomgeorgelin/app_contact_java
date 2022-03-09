package app;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String prenom;
    private String nom;
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
        return String.format("%s %s %s", prenom, nom, adresse_postale);
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse_postale() {
        return adresse_postale;
    }
}