package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionRequest;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DrinkService {
    private static final Logger log = LoggerFactory.getLogger(ConvertUtils.class);

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private IngredientCocktailRepository ingredientCocktailRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private AlcoholicRepository alcoholicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GlassRepository glassRepository;


    /*public DrinkResponse getDrinkById(Integer drinkId) {
        // Retrieve the drink entity from the repository by ID
        Drink drink = drinkRepository.getReferenceById(drinkId);

        // Map the drink entity to a DrinkResponse DTO using the mapper
        DrinkResponse drinkResponse = DrinkMapper.INSTANCE.toDrinkResponse(drink);

        return drinkResponse;
    }*/

    //Get all drink
    public List<DrinkResponse> getAllDrink() throws UnprocessableEntityException{
        log.info("Inizio Drink Service getAllDrink()");
        log.info("Ricerca nel db di tutti i drink");
        List<Drink> drinks = drinkRepository.findAll();
        log.info("Ritorno del numero di drinks: {}", drinks.size());
        if(drinks.isEmpty()){
            throw new UnprocessableEntityException("Drink repository is empty");
        }
        log.info("Inizio di mapDrinkResponse() con mapping di DrinkResponse");
        return ConvertUtils.mapDrinksResponse(drinks);
        //return DrinkMapper.INSTANCE.toDrinkResponse(drinks);
    }

    //Post create one drink
    public DrinkResponse addDrink(DrinkRequest drinkRequest) throws UnprocessableEntityException {
        Drink drink = null;
        try {
            log.info("Inizio Drink Service addDrink()");

            log.info("Ricerca nel db del tipo di alcoholic");
            Alcoholic alcoholic = alcoholicRepository.findByType(drinkRequest.isAlcoholic());
            log.info("Ritorno dell'entity alcoholic");
            if (alcoholic == null) {
                throw new UnprocessableEntityException("Alcoholic not found");
            }

            //Ricavo Category dal database usando il valore string
            String newCategory = drinkRequest.getCategory();
            log.info("Ricerca nel db del nome della category");
            Category category = categoryRepository.findByName(newCategory);
            log.info("Ritorno dell'entity category");
            if (category == null) {
                category = new Category();
                category.setName(newCategory);
                log.info("Salvataggio della nuova category nel db");
                category = categoryRepository.save(category);
                log.info("Ritorno dell'entity category");
            }

            //Ricavo Glass dal database usando il valore string
            String newGlass = drinkRequest.getGlass();
            log.info("Ricerca nel db del nome del glass");
            Glass glass = glassRepository.findByName(newGlass);
            log.info("Ritorno dell'entity glass");
            if (glass == null) {
                glass = new Glass();
                glass.setName(newGlass);
                log.info("Salvataggio del nuovo glass nel db");
                glass = glassRepository.save(glass);
                log.info("Ritorno dell'entity glass");
            }

            HashMap<String, Language> languageHashMap = new HashMap<>();
            for (InstructionRequest instructionRequest : drinkRequest.getInstructions()) {
                log.info("Ricerca nel db del nome del language");
                Language language = languageRepository.findByName(instructionRequest.getLanguage());
                log.info("Ritorno dell'entity language");
                if (language == null) {
                    throw new UnprocessableEntityException("Language not found");
                }
                languageHashMap.put(instructionRequest.getLanguage(), language);
            }

            HashMap<String, Ingredient> ingredientHashMap = new HashMap<>();
            for (IngredientCocktailRequest ingredientCocktailRequest : drinkRequest.getIngredients()) {
                log.info("Ricerca nel db del nome dell'ingredient");
                Ingredient ingredient = ingredientRepository.findByName(ingredientCocktailRequest.getName());
                log.info("Ritorno dell'entity ingredient");
                if (ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setName(drinkRequest.getName());
                    log.info("Salvataggio dell'ingrediente nel db");
                    ingredient = ingredientRepository.save(ingredient);
                    log.info("Ritorno dell'entity ingredient");
                }
                ingredientHashMap.put(ingredientCocktailRequest.getName(), ingredient);
            }

            log.info("Inizio di mapDrink() con mapping di Drink");
            drink = ConvertUtils.mapDrink(drinkRequest, alcoholic, category, glass);
            log.info("Salvataggio del drink nel db");
            drinkRepository.save(drink);

            log.info("Inizio di mapInstructionList() con mapping di Instruction");
            List<Instruction> instructions = ConvertUtils.mapInstructionList(drinkRequest, drink, languageHashMap);
            log.info("Salvataggio dell'instruction nel db");
            instructionRepository.saveAll(instructions);
            drink.setInstructions(instructions);

            log.info("Inizio di mapIngredientCocktailList() con mapping di IngredientCocktails");
            List<IngredientCocktail> ingredientCocktails = ConvertUtils.mapIngredientCocktailList(drinkRequest, drink, ingredientHashMap);
            log.info("Salvataggio dell'ingredientCocktail nel db");
            ingredientCocktailRepository.saveAll(ingredientCocktails);
            drink.setIngredientCocktails(ingredientCocktails);

            log.info("Inizio di mapDrinkResponse() con mapping di DrinkResponse");
            return ConvertUtils.mapDrinkResponse(drink);
        } catch (Exception e) {
            log.error("Error occurred while adding drink: {}", e.getMessage());
            if (drink != null) {
                log.info("Deleting the drink due to error...");

                boolean hasNullIngredientCocktails = drink.getIngredientCocktails().stream()
                        .anyMatch(ingredientCocktail -> ingredientCocktail.getId().getDrink() == null
                                || ingredientCocktail.getId().getIngredient() == null);

                // Check if instructions have null foreign keys
                boolean hasNullInstructions = drink.getInstructions().stream()
                        .anyMatch(instruction -> instruction.getDrink() == null || instruction.getLanguage() == null);
                if (hasNullIngredientCocktails || hasNullInstructions) {
                    log.warn("Some IngredientCocktails or Instructions have null foreign keys, skipping deletion.");
                    instructionRepository.deleteAll(drink.getInstructions());
                    ingredientCocktailRepository.deleteAll(drink.getIngredientCocktails());
                    log.info("Elimino ingredienti e istruzioni");
                }
                if(drink.getName() == null || drink.getCategory() == null || drink.getAlcoholic() == null || drink.getGlass() == null ){
                    log.info("Il drink presenta valori nulli");
                    drinkRepository.delete(drink);
                    log.info("Elimino il drink");
                }
            }
            throw new UnprocessableEntityException("Errore");
        }
    }
}