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
public class EmailController {
    @Autowired
    EmailRepository repository;
    @Autowired
    ContactRepository repositoryContact;
    @GetMapping("/email")
    public String newEmail(Model model) {
        model.addAttribute("emails", repository.findAll());
        model.addAttribute("contacts", repositoryContact.findAll());
        return "email";
    }

    @PostMapping("/email")
    public String postEmail(Model model, @RequestParam("email") String email,  @RequestParam("contact") Long id) {
        Optional<Contact> c = repositoryContact.findById(id);
        if(!c.isEmpty()) {
            Contact contact = c.get();
            repository.save(new Email(email,contact));
        }

        return "redirect:email";
    }

    @PostMapping("/remove-email")
    public String removeEmail(Model model, @RequestParam("id") Long id) {
        repository.deleteById(id);
        return "redirect:email";
    }
}
