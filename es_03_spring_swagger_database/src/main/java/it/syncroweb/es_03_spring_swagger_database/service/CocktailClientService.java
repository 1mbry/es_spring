package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.Languages;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailClientService {

    private static final Logger log = LoggerFactory.getLogger(CocktailClientService.class);

    @Autowired
    private AlcoholicRepository alcoholicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GlassRepository glassRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCocktailRepository ingredientCocktailRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Transactional
    public List<Drink> addDrinks(CocktailApiResponse cocktailApiResponse) {
        try {
            log.info("Inizio Cocktail ClientService.addDrinks()");
            log.info("Inizio mapping di Drink");
            return cocktailApiResponse.getDrinks().stream()
                    .map(cocktailResponse -> {
                        Drink drink = new Drink();
                        try {
                            log.info("Ricerca del nome '{}' nel db drink", cocktailResponse.getStrDrink());
                            Drink drinkEntity = drinkRepository.findByName(cocktailResponse.getStrDrink());
                            log.info("Ritorno dell'entity drink : {}", drinkEntity);
                            if (drinkEntity != null) {
                                return drinkEntity;
                            }
                            boolean isAlcoholic = "Alcoholic".equalsIgnoreCase(cocktailResponse.getStrAlcoholic());
                            log.info("Ricerca del tipo '{}' nel db alcoholic", isAlcoholic);
                            Alcoholic alcoholic = alcoholicRepository.findByType(isAlcoholic);
                            log.info("Ritorno dell'entity alcoholic : {}", alcoholic);
                            if (alcoholic == null) {
                                alcoholic = new Alcoholic();
                                alcoholic.setType(isAlcoholic);
                                log.info("Salvataggio del tipo nel db alcoholic : {}", isAlcoholic);
                                alcoholic = alcoholicRepository.save(alcoholic);
                                log.info("Ritorno dell'entity alcoholic : {}", alcoholic);
                            }

                            String newCategory = cocktailResponse.getStrCategory();
                            log.info("Ricerca del nome '{}' nel db category", newCategory);
                            Category category = categoryRepository.findByName(newCategory);
                            log.info("Ritorno dell'entity category : {}", category);
                            if (category == null) {
                                category = new Category();
                                category.setName(newCategory);
                                log.info("Salvataggio del nome nel db category : {}", newCategory);
                                category = categoryRepository.save(category);
                                log.info("Ritorno dell'entity category : {}", category);
                            }

                            String newGlass = cocktailResponse.getStrGlass();
                            log.info("Ricerca del nome '{}' nel db glass", newGlass);
                            Glass glass = glassRepository.findByName(newGlass);
                            log.info("Ritorno dell'enity glass : {}", glass);
                            if (glass == null) {
                                glass = new Glass();
                                glass.setName(newGlass);
                                log.info("Salvataggio del nome nel db glass : {}", newGlass);
                                glass = glassRepository.save(glass);
                                log.info("Ritorno dell'entity glass : {}", glass);
                            }


                            //salvataggio del Drink
                            drink.setName(cocktailResponse.getStrDrink());
                            drink.setAlternate_name(cocktailResponse.getStrDrinkAlternate());
                            drink.setAlcoholic(alcoholic);
                            drink.setCategory(category);
                            drink.setGlass(glass);
                            drink.setUrl_thumb(cocktailResponse.getStrDrinkThumb());
                            drink.setImage_attribution(cocktailResponse.getStrImageAttribution());
                            drink.setImage_source(cocktailResponse.getStrImageSource());
                            drink.setVideo(cocktailResponse.getStrVideo());
                            drink.setTags(cocktailResponse.getStrTags());
                            drink.setIba(cocktailResponse.getStrIBA());
                            drink.setCreative_commons(cocktailResponse.getStrCreativeCommonsConfirmed());
                            log.info("Salvataggio del drink nel db drink : {}", drink);
                            drinkRepository.save(drink);

                            log.info("Inizio ciclo per le instruction and language");
                            Arrays.stream(cocktailResponse.getClass().getDeclaredFields())
                                    .filter(field -> field.getName().startsWith("strInstructions"))
                                    .forEach(field -> {
                                        String languageCode = field.getName().substring("strInstructions".length());
                                        String languageName = Languages.getNameOrDefault(languageCode);

                                        String instruction = (String) Utils.getFieldByName(cocktailResponse, field.getName());

                                        // Se l'istruzione è nulla, impostiamo una stringa predefinita
                                        if (instruction == null || instruction.isEmpty()) {
                                            instruction = "No instructions available";
                                        }

                                        log.info("Ricerca del nome '{}' nel db language", languageName);
                                        Language language = languageRepository.findByName(languageName);
                                        log.info("Ritorno dell'entity language : {}", language);
                                        if (language == null) {
                                            language = new Language();
                                            language.setName(languageName);
                                            log.info("Salvataggio del nome nel db language : {}", languageName);
                                            language = languageRepository.save(language);
                                            log.info("Ritorno dell'entity language : {}", language);
                                        }

                                        Instruction instruction1 = new Instruction();
                                        instruction1.setText(instruction);
                                        instruction1.setLanguage(language);
                                        instruction1.setDrink(drink);
                                        log.info("Salvataggio dell'instruction nel db instruction : {}", instruction1);
                                        instructionRepository.save(instruction1);
                                    });

                            log.info("Inizio ciclo per ingredient e ingredientCocktail");
                            Arrays.stream(cocktailResponse.getClass().getDeclaredFields())
                                    .filter(field -> field.getName().startsWith("strIngredient") || field.getName().startsWith("strMeasure"))
                                    .forEach(field -> {
                                        String fieldName = field.getName();
                                        if (fieldName.startsWith("strIngredient")) {
                                            int index = Integer.parseInt(fieldName.substring("strIngredient".length()));
                                            String ingredientName = (String) Utils.getFieldByName(cocktailResponse, fieldName);

                                            if (ingredientName != null && !ingredientName.isEmpty()) {

                                                log.info("Ricerca del nome '{}' nel db ingredient", ingredientName);
                                                Ingredient ingredient = ingredientRepository.findByName(ingredientName);
                                                log.info("Ritorno dell'entity ingredient : {}", ingredient);
                                                if (ingredient == null) {
                                                    ingredient = new Ingredient();
                                                    ingredient.setName(ingredientName);
                                                    log.info("Salvataggio del nome nel db ingredient : {}", ingredientName);
                                                    ingredient = ingredientRepository.save(ingredient);
                                                    log.info("Ritorno dell'entity ingredient : {}", ingredient);
                                                }

                                                String measureFieldName = "strMeasure" + index;
                                                String measureValue = (String) Utils.getFieldByName(cocktailResponse, measureFieldName);

                                                if (measureValue != null && !measureValue.isEmpty()) {
                                                    IngredientCocktail ingredientCocktail = new IngredientCocktail();
                                                    IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
                                                    ingredientCocktailId.setDrink(drink);
                                                    ingredientCocktailId.setIngredient(ingredient);
                                                    ingredientCocktail.setId(ingredientCocktailId);
                                                    ingredientCocktail.setIngredient(ingredient);
                                                    ingredientCocktail.setMeasure(measureValue);
                                                    log.info("Salvataggio dell ingredientCocktail nel db ingredient_cocktail : {}", ingredientCocktail);
                                                    ingredientCocktailRepository.save(ingredientCocktail);
                                                }
                                            }
                                        }
                                    });
                        }catch (Exception e){
                            log.info("Errore nella lettura del drink");

                            boolean hasNullIngredientCocktails = drink.getIngredientCocktails().stream()
                                    .anyMatch(ingredientCocktail -> ingredientCocktail.getId().getDrink() == null
                                            || ingredientCocktail.getId().getIngredient() == null);

                            boolean hasNullInstructions = drink.getInstructions().stream()
                                    .anyMatch(instruction -> instruction.getDrink() == null || instruction.getLanguage() == null);
                            if (hasNullIngredientCocktails || hasNullInstructions) {
                                log.warn("Ingredients e Instructions presentano valori nulli");
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
                        log.info("Ritorno del drink dopo aver settato i valori : {}", drink);
                        return drink;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}