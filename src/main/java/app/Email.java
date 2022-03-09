package app;

import javax.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;
    @ManyToOne
    @JoinColumn(name="contact_fk")
    private Contact contact;

    protected Email() {}

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Email[id=%d, email='%s']", id, email);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setClient(Contact contact) {
        this.contact = contact;
    }

    public Contact getClient() {
        return contact;
    }
}