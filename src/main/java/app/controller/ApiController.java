package app.controller;

import app.entity.Contact;
import app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/xml")
public class ApiController implements WebMvcConfigurer {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value="", produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(value="action", required = true) String action, @RequestParam(value="id", required = false) Long id) {
        System.out.println(action);
        switch(action) {
            case "listContacts":
                try {
                    List<Contact> contacts = new ArrayList<>();
                    contactRepository.findAll().forEach(contacts::add);
                    return new ResponseEntity<>(contacts, HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            case "getContact":
                if(id == null) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                try {
                    List<Contact> contacts = new ArrayList<>();
                    Optional<Contact> contact = contactRepository.findById(id);
                    if(contact.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                    contacts.add(contact.get());
                    return new ResponseEntity<>(contacts, HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            case "delContact":
                System.out.println("jarrive ici");
                if(id == null) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                try {
                    contactRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            default:
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}