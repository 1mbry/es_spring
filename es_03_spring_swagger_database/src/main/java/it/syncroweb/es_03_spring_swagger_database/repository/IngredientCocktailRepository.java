package it.syncroweb.es_03_spring_swagger_database.repository;

import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktail;
import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientCocktailRepository extends JpaRepository<IngredientCocktail, IngredientCocktailId> {

}
