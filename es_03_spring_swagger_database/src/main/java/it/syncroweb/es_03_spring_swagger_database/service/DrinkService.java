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
    private static final Logger logger = LoggerFactory.getLogger(DrinkService.class);

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


    //Get all drink
    public List<DrinkResponse> getAllDrink() throws UnprocessableEntityException{
        logger.info("Inizio Drink Service getAllDrink()");
        logger.info("Ricerca nel db di tutti i drink");
        List<Drink> drinks = drinkRepository.findAll();
        logger.info("Ritorno di tutte le entity di drink");
        if(drinks.isEmpty()){
            throw new UnprocessableEntityException("Drink repository is empty");
        }
        logger.info("Inizio di mapDrinkResponse() con mapping di DrinkResponse");
        return ConvertUtils.mapDrinksResponse(drinks);
    }

    //Post create one drink
    public DrinkResponse addDrink(DrinkRequest drinkRequest) throws UnprocessableEntityException {
        try {
            logger.info("Inizio Drink Service addDrink()");

            logger.info("Ricerca nel db del tipo di alcoholic");
            Alcoholic alcoholic = alcoholicRepository.findByType(drinkRequest.isAlcoholic());
            logger.info("Ritorno dell'entity alcoholic");
            if(alcoholic == null){
                throw new UnprocessableEntityException("Alcoholic not found");
            }

            //Ricavo Category dal database usando il valore string
            String newCategory = drinkRequest.getCategory();
            logger.info("Ricerca nel db del nome della category");
            Category category = categoryRepository.findByName(newCategory);
            logger.info("Ritorno dell'entity category");
            if(category == null){
                category = new Category();
                category.setName(newCategory);
                logger.info("Salvataggio della nuova category nel db");
                category = categoryRepository.save(category);
                logger.info("Ritorno dell'entity category");
            }

            //Ricavo Glass dal database usando il valore string
            String newGlass = drinkRequest.getGlass();
            logger.info("Ricerca nel db del nome del glass");
            Glass glass = glassRepository.findByName(newGlass);
            logger.info("Ritorno dell'entity glass");
            if(glass == null){
                glass = new Glass();
                glass.setName(newGlass);
                logger.info("Salvataggio del nuovo glass nel db");
                glass = glassRepository.save(glass);
                logger.info("Ritorno dell'entity glass");
            }

            HashMap<String,Language> languageHashMap = new HashMap<>();
            for (InstructionRequest instructionRequest : drinkRequest.getInstructions()) {
                logger.info("Ricerca nel db del nome del language");
                Language language = languageRepository.findByName(instructionRequest.getLanguage());
                logger.info("Ritorno dell'entity language");
                if (language == null) {
                    throw new UnprocessableEntityException("Language not found");
                }
                languageHashMap.put(instructionRequest.getLanguage(),language);
            }

            HashMap<String,Ingredient> ingredientHashMap = new HashMap<>();
            for (IngredientCocktailRequest ingredientCocktailRequest : drinkRequest.getIngredients()) {
                logger.info("Ricerca nel db del nome dell'ingredient");
                Ingredient ingredient = ingredientRepository.findByName(ingredientCocktailRequest.getName());
                logger.info("Ritorno dell'entity ingredient");
                if(ingredient == null){
                    ingredient = new Ingredient();
                    ingredient.setName(drinkRequest.getName());
                    logger.info("Salvataggio dell'ingrediente nel db");
                    ingredient = ingredientRepository.save(ingredient);
                    logger.info("Ritorno dell'entity ingredient");
                }
                ingredientHashMap.put(ingredientCocktailRequest.getName(),ingredient);
            }

            logger.info("Inizio di mapDrink() con mapping di Drink");
            Drink drink = ConvertUtils.mapDrink(drinkRequest,alcoholic,category,glass);
            logger.info("Salvataggio del drink nel db");
            drinkRepository.save(drink);

            logger.info("Inizio di mapInstructionList() con mapping di Instruction");
            List<Instruction> instructions = ConvertUtils.mapInstructionList(drinkRequest, drink, languageHashMap);
            logger.info("Salvataggio dell'instruction nel db");
            instructionRepository.saveAll(instructions);
            drink.setInstructions(instructions);

            logger.info("Inizio di mapIngredientCocktailList() con mapping di IngredientCocktails");
            List<IngredientCocktail> ingredientCocktails = ConvertUtils.mapIngredientCocktailList(drinkRequest, drink, ingredientHashMap);
            logger.info("Salvataggio dell'ingredientCocktail nel db");
            ingredientCocktailRepository.saveAll(ingredientCocktails);
            drink.setIngredientCocktails(ingredientCocktails);

            logger.info("Inizio di mapDrinkResponse() con mapping di DrinkResponse");
            return ConvertUtils.mapDrinkResponse(drink);
        }catch (Exception e) {
            throw new UnprocessableEntityException("Errore");
            //drinkRepository.delete(drink);
        }
    }
}

