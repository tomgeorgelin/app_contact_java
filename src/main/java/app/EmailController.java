package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
    @Autowired
    EmailRepository repository;
    @GetMapping("/email")
    public String newEmail(Model model) {
        model.addAttribute("emails", repository.findAll());
        return "email";
    }

    @PostMapping("/email")
    public String postEmail(Model model, @RequestParam("email") String email) {
        repository.save(new Email(email));
        return "redirect:email";
    }
}
