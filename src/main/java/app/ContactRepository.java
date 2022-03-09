package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByNom(String nom);

    Contact findById(long id);
}