package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.Languages;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.FormatLogger;
import it.syncroweb.es_03_spring_swagger_database.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailClientService {

    private static final FormatLogger logger = new FormatLogger(LogManager.getLogger(CocktailClientService.class));

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
            logger.info("Inizio Cocktail Client Service addDrinks()");
            logger.info("Inizio di cocktailApiResponse.getDrinks().stream() con mapping di Drink");
            return cocktailApiResponse.getDrinks().stream()
                    .map(cocktailResponse -> {
                        Drink drink = new Drink();
                        try {
                            logger.info("Ricerca del nome nel db drink");
                            Drink drinkEntity = drinkRepository.findByName(cocktailResponse.getStrDrink());
                            logger.info("Ritorno dell'entity drink");
                            if (drinkEntity != null) {
                                return drinkEntity;
                            }
                            boolean isAlcoholic = "Alcoholic".equalsIgnoreCase(cocktailResponse.getStrAlcoholic());
                            logger.info("Ricerca del tipo nel db alcoholic");
                            Alcoholic alcoholic = alcoholicRepository.findByType(isAlcoholic);
                            logger.info("Ritorno dell'entity alcoholic");
                            if (alcoholic == null) {
                                alcoholic = new Alcoholic();
                                alcoholic.setType(isAlcoholic);
                                logger.info("Salvataggio del tipo nel db alcoholic", alcoholic);
                                alcoholic = alcoholicRepository.save(alcoholic);
                                logger.info("Ritorno dell'entity alcoholic", alcoholic.getId());
                            }

                            String newCategory = cocktailResponse.getStrCategory();
                            logger.info("Ricerca del nome nel db category");
                            Category category = categoryRepository.findByName(newCategory);
                            logger.info("Ritorno dell'entity category");
                            if (category == null) {
                                category = new Category();
                                category.setName(newCategory);
                                logger.info("Salvataggio del nome nel db category");
                                category = categoryRepository.save(category);
                                logger.info("Ritorno dell'entity category");
                            }

                            String newGlass = cocktailResponse.getStrGlass();
                            logger.info("Ricerca del nome nel db glass");
                            Glass glass = glassRepository.findByName(newGlass);
                            logger.info("Ritorno dell'enity glass");
                            if (glass == null) {
                                glass = new Glass();
                                glass.setName(newGlass);
                                logger.info("Salvataggio del nome nel db glass");
                                glass = glassRepository.save(glass);
                                logger.info("Ritorno dell'entity glass");
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
                            logger.info("Salvataggio del drink nel db drink");
                            drinkRepository.save(drink);

                            logger.info("Inizio Arrays.stream() per le instruction and language");
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

                                        logger.info("Ricerca del nome nel db language");
                                        Language language = languageRepository.findByName(languageName);
                                        logger.info("Ritorno dell'entity language");
                                        if (language == null) {
                                            language = new Language();
                                            language.setName(languageName);
                                            logger.info("Salvataggio del nome nel db language");
                                            language = languageRepository.save(language);
                                            logger.info("Ritorno dell'entity language");
                                        }

                                        Instruction instruction1 = new Instruction();
                                        instruction1.setText(instruction);
                                        instruction1.setLanguage(language);
                                        instruction1.setDrink(drink);
                                        logger.info("Salvataggio dell'instruction nel db instruction");
                                        instructionRepository.save(instruction1);
                                    });

                            logger.info("Inizio Arrays.stream() per ingredient e ingredientCocktail");
                            Arrays.stream(cocktailResponse.getClass().getDeclaredFields())
                                    .filter(field -> field.getName().startsWith("strIngredient") || field.getName().startsWith("strMeasure"))
                                    .forEach(field -> {
                                        String fieldName = field.getName();
                                        if (fieldName.startsWith("strIngredient")) {
                                            int index = Integer.parseInt(fieldName.substring("strIngredient".length()));
                                            String ingredientName = (String) Utils.getFieldByName(cocktailResponse, fieldName);

                                            if (ingredientName != null && !ingredientName.isEmpty()) {

                                                logger.info("Ricerca del nome nel db ingredient");
                                                Ingredient ingredient = ingredientRepository.findByName(ingredientName);
                                                logger.info("Ritorno dell'entity ingredient");
                                                if (ingredient == null) {
                                                    ingredient = new Ingredient();
                                                    ingredient.setName(ingredientName);
                                                    logger.info("Salvataggio del nome nel db ingredient");
                                                    ingredient = ingredientRepository.save(ingredient);
                                                    logger.info("Ritorno dell'entity ingredient");
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
                                                    logger.info("Salvataggio dell ingredientCocktail nel db ingredient_cocktail");
                                                    ingredientCocktailRepository.save(ingredientCocktail);
                                                }
                                            }
                                        }
                                    });
                            logger.info("Ritorno del drink dopo aver settato i valori");
                        }catch (Exception e){
                            logger.info("Deleting the drink due to error...");

                            boolean hasNullIngredientCocktails = drink.getIngredientCocktails().stream()
                                    .anyMatch(ingredientCocktail -> ingredientCocktail.getId().getDrink() == null
                                            || ingredientCocktail.getId().getIngredient() == null);

                            boolean hasNullInstructions = drink.getInstructions().stream()
                                    .anyMatch(instruction -> instruction.getDrink() == null || instruction.getLanguage() == null);
                            if (hasNullIngredientCocktails || hasNullInstructions) {
                                logger.warn("Ingredients e Instructions presentano valori nulli");
                                instructionRepository.deleteAll(drink.getInstructions());
                                ingredientCocktailRepository.deleteAll(drink.getIngredientCocktails());
                                logger.info("Elimino ingredienti e istruzioni");
                            }
                            if(drink.getName() == null || drink.getCategory() == null || drink.getAlcoholic() == null || drink.getGlass() == null ){
                                logger.info("Il drink presenta valori nulli");
                                drinkRepository.delete(drink);
                                logger.info("Elimino il drink");
                            }
                        }
                        return drink;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            //drinkRepository.delete(drink);
            throw new RuntimeException(e);
        }
    }
}