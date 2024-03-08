package it.syncroweb.logintest.repository;

import it.syncroweb.logintest.model.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    EmailToken findByToken(String token);
}
