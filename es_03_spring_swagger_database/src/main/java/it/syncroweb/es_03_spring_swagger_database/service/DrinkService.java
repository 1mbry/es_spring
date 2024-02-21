package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionRequest;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.mapper.DrinkMapper;
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

    //Get all drink usando MapStruct oppure ConvertUtils
    public List<DrinkResponse> getAllDrink() throws UnprocessableEntityException{
        log.info("Inizio DrinkService.getAllDrink()");
        log.info("Ricerca nel db di tutti i drink");
        List<Drink> drinks = drinkRepository.findAll();
        log.info("Ritorno del numero di drinks: {}", drinks.size());
        if(drinks.isEmpty()){
            throw new UnprocessableEntityException("Drink repository is empty");
        }
        log.info("Inizio mapping di DrinkResponse");
        //return ConvertUtils.mapDrinksResponse(drinks);
        return DrinkMapper.INSTANCE.mapDrinkList(drinks);
    }

    //Post create one drink
    public DrinkResponse addDrink(DrinkRequest drinkRequest) throws UnprocessableEntityException {
        Drink drink = null;
        try {
            log.info("Inizio DrinkService.addDrink()");

            log.info("Ricerca nel db del tipo di alcoholic : {}", drinkRequest.isAlcoholic());
            Alcoholic alcoholic = alcoholicRepository.findByType(drinkRequest.isAlcoholic());
            log.info("Ritorno dell'entity alcoholic : {}", alcoholic);
            if (alcoholic == null) {
                throw new UnprocessableEntityException("Alcoholic not found");
            }

            //Ricavo Category dal database usando il valore string
            String newCategory = drinkRequest.getCategory();
            log.info("Ricerca nel db del nome della category : {}", newCategory);
            Category category = categoryRepository.findByName(newCategory);
            log.info("Ritorno dell'entity category : {}", category);
            if (category == null) {
                category = new Category();
                category.setName(newCategory);
                log.info("Salvataggio della nuova category nel db : {}", category);
                category = categoryRepository.save(category);
                log.info("Ritorno dell'entity category : {}", category);
            }

            //Ricavo Glass dal database usando il valore string
            String newGlass = drinkRequest.getGlass();
            log.info("Ricerca nel db del nome del glass : {}", newGlass);
            Glass glass = glassRepository.findByName(newGlass);
            log.info("Ritorno dell'entity glass : {}", glass);
            if (glass == null) {
                glass = new Glass();
                glass.setName(newGlass);
                log.info("Salvataggio del nuovo glass nel db : {}", glass);
                glass = glassRepository.save(glass);
                log.info("Ritorno dell'entity glass : {}", glass);
            }

            HashMap<String, Language> languageHashMap = new HashMap<>();
            for (InstructionRequest instructionRequest : drinkRequest.getInstructions()) {
                log.info("Ricerca nel db del nome del language : {}", instructionRequest.getLanguage());
                Language language = languageRepository.findByName(instructionRequest.getLanguage());
                log.info("Ritorno dell'entity language : {}", language);
                if (language == null) {
                    throw new UnprocessableEntityException("Language not found");
                }
                languageHashMap.put(instructionRequest.getLanguage(), language);
            }

            HashMap<String, Ingredient> ingredientHashMap = new HashMap<>();
            for (IngredientCocktailRequest ingredientCocktailRequest : drinkRequest.getIngredients()) {
                log.info("Ricerca nel db del nome dell'ingredient : {}", ingredientCocktailRequest.getName());
                Ingredient ingredient = ingredientRepository.findByName(ingredientCocktailRequest.getName());
                log.info("Ritorno dell'entity ingredient : {}", ingredient);
                if (ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setName(drinkRequest.getName());
                    log.info("Salvataggio dell'ingrediente nel db : {}", ingredient);
                    ingredient = ingredientRepository.save(ingredient);
                    log.info("Ritorno dell'entity ingredient : {}", ingredient);
                }
                ingredientHashMap.put(ingredientCocktailRequest.getName(), ingredient);
            }

            log.info("Inizio mapping di Drink");
            drink = ConvertUtils.mapDrink(drinkRequest, alcoholic, category, glass);
            log.info("Salvataggio del drink nel db : {}", drink);
            drinkRepository.save(drink);

            log.info("Inizio mapping di Instruction");
            List<Instruction> instructions = ConvertUtils.mapInstructionList(drinkRequest, drink, languageHashMap);
            log.info("Salvataggio dell'instruction nel db : {}", instructions);
            instructionRepository.saveAll(instructions);
            drink.setInstructions(instructions);

            log.info("Inizio mapping di IngredientCocktails");
            List<IngredientCocktail> ingredientCocktails = ConvertUtils.mapIngredientCocktailList(drinkRequest, drink, ingredientHashMap);
            log.info("Salvataggio dell'ingredientCocktail nel db : {}", ingredientCocktails);
            ingredientCocktailRepository.saveAll(ingredientCocktails);
            drink.setIngredientCocktails(ingredientCocktails);

            log.info("Inizio mapping di DrinkResponse:{}", drink);
            //return ConvertUtils.mapDrinkResponse(drink);
            return DrinkMapper.INSTANCE.mapDrink(drink);
        } catch (Exception e) {
            log.error("Errore durante l'aggiunta del drink: {}", e.getMessage());
            if (drink != null) {
                boolean hasNullIngredientCocktails = drink.getIngredientCocktails().stream()
                        .anyMatch(ingredientCocktail -> ingredientCocktail.getId().getDrink() == null
                                || ingredientCocktail.getId().getIngredient() == null);

                boolean hasNullInstructions = drink.getInstructions().stream()
                        .anyMatch(instruction -> instruction.getDrink() == null || instruction.getLanguage() == null);
                if (hasNullIngredientCocktails || hasNullInstructions) {
                    log.warn("IngredientCocktails or Instructions presenta foreign keys nulle");
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