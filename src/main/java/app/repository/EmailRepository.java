package app.repository;

import java.util.List;

import app.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByEmail(String email);

    Email findById(long id);
}