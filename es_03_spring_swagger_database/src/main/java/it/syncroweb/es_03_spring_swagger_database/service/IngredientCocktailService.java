package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktail;
import it.syncroweb.es_03_spring_swagger_database.repository.IngredientCocktailRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientCocktailService {

    @Autowired
    public IngredientCocktailRepository ingredientCocktailRepository;
    public CategoryRepository categoryRepository;

    //Get all booze
   /* public ResponseEntity<List<IngredientsDTO>> getAllBooze(){
        try {
            List<Ingredients> ingredients = ingredientsRepository.findAll();
            List<IngredientsDTO> ingredientsDTOS = ingredients
                    .stream()
                    .map(this::convertBoozeToDTO)
                    .collect(Collectors.toList());

            List<Category> categories = categoryRepository.findAll();

            return new ResponseEntity<>(ingredientsDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }*/

    public ResponseEntity<List<IngredientCocktail>> getIngredients(){
        List<IngredientCocktail> ingredientCocktails = ingredientCocktailRepository.findAll();
        return new ResponseEntity<>(ingredientCocktails, HttpStatus.OK);
    }

    //Post create one booze
    public ResponseEntity<IngredientCocktail> addBooze(IngredientCocktail ingredientCocktail){
        try{
            IngredientCocktail ingredientCocktail1 = ingredientCocktailRepository.save(ingredientCocktail);
            return new ResponseEntity<>(ingredientCocktail1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<IngredientCocktail>> addAllBooze(List<IngredientCocktail> ingredients) {
        try {
            List<IngredientCocktail> boozes1 = ingredientCocktailRepository.saveAll(ingredients);
            return new ResponseEntity<>(boozes1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


  /*  private IngredientsDTO convertBoozeToDTO(Ingredients ingredients){
        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setDrink_name(ingredients.getDrink().getName());
        ingredientsDTO.setIngredient_name(ingredients.getIngredient().getName());
        ingredientsDTO.setMeasure(ingredients.getMeasure());
        return ingredientsDTO;
    }*/
}
