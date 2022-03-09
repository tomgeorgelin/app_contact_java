package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getClients(Model model) {
        model.addAttribute("contacts", repository.findAll());
        return "contacts";
    }

    @GetMapping("/show")
    public String showClient(Model model, @RequestParam(value = "id",required = false) Long id) {
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
    public String createClient(Model model, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("adresse") String adresse) {
        repository.save(new Contact(prenom,nom,adresse));
        return "redirect:contacts";
    }

    @PostMapping("/remove")
    public String removeClient(Model model, @RequestParam("id") Long id) {
        repository.deleteById(id);
        return "redirect:contacts";
    }
}
