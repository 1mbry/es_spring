package it.syncroweb.es_03_spring_swagger_database.repository;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholicRepository extends JpaRepository<Alcoholic,Integer> {

    @Query(value = "SELECT a FROM Alcoholic a where a.type = :value")
    Alcoholic findByValue(@Param("value") boolean value);
}
