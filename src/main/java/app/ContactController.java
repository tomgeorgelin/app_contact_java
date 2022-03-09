package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    @Autowired
    ContactRepository repository;
    @GetMapping("/contacts")
    public String getClients(Model model) {
        repository.save(new Contact("jean","pierre","jean@pierre.com"));
        model.addAttribute("contacts", repository.findAll());
        return "contacts";
    }

    @PostMapping("/contact")
    public String postClient(Model model) {
        repository.save(new Contact("jean","pierre","jean@pierre.com"));
        return "redirect:contacts";
    }
}
