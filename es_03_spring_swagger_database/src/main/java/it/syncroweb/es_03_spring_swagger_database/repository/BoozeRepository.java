package it.syncroweb.es_03_spring_swagger_database.repository;

import it.syncroweb.es_03_spring_swagger_database.model.Booze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoozeRepository extends JpaRepository<Booze,Integer> {

}
