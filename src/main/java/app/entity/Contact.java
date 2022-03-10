package app.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String prenom;
    @NotNull
    private String nom;

    @NotNull
    private String adressePostale;

    @ElementCollection
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;

    protected Contact() {}

    public Contact(String prenom, String nom, String adressePostale) {
        this.prenom = prenom;
        this.nom = nom;
        this.adressePostale = adressePostale;
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

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s", id, prenom, nom, adressePostale, this.emails != null ? emails.toString() : "");
    }

}