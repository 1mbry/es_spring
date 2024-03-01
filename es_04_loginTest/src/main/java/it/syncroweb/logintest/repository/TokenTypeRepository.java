package it.syncroweb.logintest.repository;

import it.syncroweb.logintest.model.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenTypeRepository extends JpaRepository<TokenType, Long> {

    Optional<TokenType> findByName(String name);
}
