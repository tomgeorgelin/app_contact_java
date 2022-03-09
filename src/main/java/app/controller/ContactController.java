package app.controller;

import app.repository.ContactRepository;
import app.repository.EmailRepository;
import app.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ContactController {
    @Autowired
    ContactRepository repository;
    @Autowired
    EmailRepository repositoryEmail;
    @GetMapping("/contacts")
    public String getContacts(Model model) {
        model.addAttribute("contacts", repository.findAll());
        return "contacts";
    }

    @GetMapping("/show")
    public String showContact(Model model, @RequestParam(value = "id",required = false) Long id) {
        if(id != null) {
            Optional<Contact> c = repository.findById(id);
            if(!c.isEmpty()) {
                model.addAttribute("contact", c.get());
            }
            else {
                model.addAttribute("contact", null);
            }
        }

        model.addAttribute("emails",repositoryEmail.findAll());

        return "create";
    }

    @PostMapping("/create")
    public String createContact(Model model, @RequestParam(value = "id",required = false) Long id, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("adresse") String adresse) {
        if(id != null) {
            Optional<Contact> c = repository.findById(id);
            if(!c.isEmpty()) {
                Contact contact = c.get();
                contact.setNom(nom);
                contact.setPrenom(prenom);
                contact.setAdressePostale(adresse);
                repository.save(contact);
            }
        }
        else {
            repository.save(new Contact(prenom,nom,adresse));
        }

        return "redirect:contacts";
    }

    @PostMapping("/remove")
    public String removeContact(Model model, @RequestParam("id") Long id) {
        repository.deleteById(id);
        return "redirect:contacts";
    }
}
