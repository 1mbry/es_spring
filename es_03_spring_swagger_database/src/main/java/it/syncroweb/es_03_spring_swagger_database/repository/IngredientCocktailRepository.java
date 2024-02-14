package it.syncroweb.es_03_spring_swagger_database.repository;

import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktail;
import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientCocktailRepository extends JpaRepository<IngredientCocktail, IngredientCocktailId> {

}
