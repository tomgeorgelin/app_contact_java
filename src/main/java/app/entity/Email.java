package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Embeddable
@XmlRootElement
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact_fk")
    private Contact contact;

    protected Email() {}

    public Email(String email, Contact contact) {
        this.email = email;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format("Email[id=%d, email='%s']", id, email);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}