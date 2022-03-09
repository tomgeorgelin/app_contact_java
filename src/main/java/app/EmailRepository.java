package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {

    List<Email> findByEmail(String email);

    Email findById(long id);
}