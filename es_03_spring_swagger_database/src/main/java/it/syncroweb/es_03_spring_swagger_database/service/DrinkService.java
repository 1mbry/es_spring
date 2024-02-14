package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionRequest;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.ConvertUtils;
import it.syncroweb.es_03_spring_swagger_database.utils.FormatLogger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DrinkService {
    private static final FormatLogger logger = new FormatLogger(LogManager.getLogger(DrinkService.class));

    @Autowired
    public DrinkRepository drinkRepository;

    @Autowired
    public IngredientCocktailRepository ingredientCocktailRepository;

    @Autowired
    public InstructionRepository instructionRepository;

    @Autowired
    public AlcoholicRepository alcoholicRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public IngredientRepository ingredientRepository;

    @Autowired
    public LanguageRepository languageRepository;

    @Autowired
    public GlassRepository glassRepository;


    //Get all drink
    public List<DrinkResponse> getAllDrink() throws UnprocessableEntityException{
        List<Drink> drinks = drinkRepository.findAll();
        if(drinks.isEmpty()){
            throw new UnprocessableEntityException("Drink repository is empty");
        }
        return ConvertUtils.mapDrinksResponse(drinks);
    }

    //Post create one drink
    public DrinkResponse addDrink(DrinkRequest drinkRequest) throws UnprocessableEntityException {
            //Ricavo Alcoholic dal database usando il valore booleano
            Alcoholic alcoholic = alcoholicRepository.findByType(drinkRequest.isAlcoholic());
            if(alcoholic == null){
                throw new UnprocessableEntityException("Alcoholic not found");
            }
            //Ricavo Category dal database usando il valore string
            Category category = categoryRepository.findByName(drinkRequest.getCategory());
            if(category == null){
                throw new UnprocessableEntityException("Category not found");
            }

            //Ricavo Glass dal database usando il valore string
            Glass glass = glassRepository.findByName(drinkRequest.getGlass());
            if(glass == null){
                throw new UnprocessableEntityException("Glass not found");
            }

            HashMap<String,Language> languageHashMap = new HashMap<>();
            //ciclo istruzioni
            for (InstructionRequest instructionRequest : drinkRequest.getInstruction()) {
                Language language = languageRepository.findByName(instructionRequest.getLanguage());
                if (language == null) {
                    throw new UnprocessableEntityException("Language not found");
                }
            languageHashMap.put(instructionRequest.getLanguage(),language);
            }

            HashMap<String,Ingredient> ingredientHashMap = new HashMap<>();
            //ciclo istruzioni
            for (IngredientCocktailRequest ingredientCocktailRequest : drinkRequest.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findByName(ingredientCocktailRequest.getName());
                if(ingredient == null){
                    throw new UnprocessableEntityException("Ingredient not found");
                }
                ingredientHashMap.put(ingredientCocktailRequest.getName(),ingredient);
            }

            Drink drink = ConvertUtils.convertDrinkRequestDTOToEntity(drinkRequest,alcoholic,category,glass);
            drinkRepository.save(drink);

            List<Instruction> instructions = ConvertUtils.converter(drinkRequest, drink, languageHashMap);
            instructionRepository.saveAll(instructions);
            drink.setInstructions(instructions);

            List<IngredientCocktail> ingredientCocktails = ConvertUtils.converters(drinkRequest, drink, ingredientHashMap);
            ingredientCocktailRepository.saveAll(ingredientCocktails);
            drink.setIngredientCocktails(ingredientCocktails);

            //logger.info("cocktail nome: %s", drink.getName());
            return ConvertUtils.mapDrinkResponse(drink);
    }

    //aggiungo i drink manualmente
    public List<Drink> addAllDrink(List<Drink> drinks) throws UnprocessableEntityException{
            List<Drink> drinks1 = drinkRepository.saveAll(drinks);
            if (drinks1.isEmpty()){
                throw new UnprocessableEntityException("I drink non sono stati salvati");
            }
            return drinks1;
    }

}

