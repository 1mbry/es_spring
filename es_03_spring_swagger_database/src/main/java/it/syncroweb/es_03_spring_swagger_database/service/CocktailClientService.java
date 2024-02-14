package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.Languages;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailClientService {

    @Autowired
    public AlcoholicRepository alcoholicRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public GlassRepository glassRepository;

    @Autowired
    public LanguageRepository languageRepository;

    @Autowired
    public InstructionRepository instructionRepository;

    @Autowired
    public IngredientRepository ingredientRepository;

    @Autowired
    public IngredientCocktailRepository ingredientCocktailRepository;

    @Autowired
    public DrinkRepository drinkRepository;

    public List<Drink> addDrinks(CocktailApiResponse cocktailApiResponse) {

        return cocktailApiResponse.getDrinks().stream()
                .map(cocktailResponse -> {

                    Drink drinkEntity = drinkRepository.findByName(cocktailResponse.getStrDrink());
                    if(drinkEntity != null){
                        return drinkEntity;
                    }
                    boolean isAlcoholic = "Alcoholic".equalsIgnoreCase(cocktailResponse.getStrAlcoholic());
                    Alcoholic alcoholic = alcoholicRepository.findByType(isAlcoholic);
                    if(alcoholic == null){
                        alcoholic = new Alcoholic();
                        alcoholic.setType(isAlcoholic);
                        alcoholic = alcoholicRepository.save(alcoholic);
                    }

                    String newCategory = cocktailResponse.getStrCategory();
                    Category category = categoryRepository.findByName(newCategory);
                    if(category == null){
                        category = new Category();
                        category.setName(newCategory);
                        category = categoryRepository.save(category);
                    }

                    String newGlass = cocktailResponse.getStrGlass();
                    Glass glass = glassRepository.findByName(newGlass);
                    if(glass == null){
                        glass = new Glass();
                        glass.setName(newGlass);
                        glass = glassRepository.save(glass);
                    }


                    //salvataggio del Drink
                    Drink drink = new Drink();
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
                    drinkRepository.save(drink);

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

                            Language language = languageRepository.findByName(languageName);
                            if (language == null) {
                                language = new Language();
                                language.setName(languageName);
                                language = languageRepository.save(language);
                            }

                            Instruction instruction1 = new Instruction();
                            instruction1.setText(instruction);
                            instruction1.setLanguage(language);
                            instruction1.setDrink(drink);
                            instructionRepository.save(instruction1);
                    });

                    Arrays.stream(cocktailResponse.getClass().getDeclaredFields())
                            .filter(field -> field.getName().startsWith("strIngredient") || field.getName().startsWith("strMeasure"))
                            .forEach(field -> {
                                String fieldName = field.getName();
                                if(fieldName.startsWith("strIngredient")){
                                    int index = Integer.parseInt(fieldName.substring("strIngredient".length()));
                                    String ingredientName = (String) Utils.getFieldByName(cocktailResponse,fieldName);

                                    if (ingredientName != null && !ingredientName.isEmpty()){

                                        Ingredient ingredient = ingredientRepository.findByName(ingredientName);
                                        if(ingredient == null){
                                            ingredient = new Ingredient();
                                            ingredient.setName(ingredientName);
                                            ingredient = ingredientRepository.save(ingredient);
                                        }

                                        String measureFieldName= "strMeasure" + index;
                                        String measureValue = (String) Utils.getFieldByName(cocktailResponse, measureFieldName);

                                        if(measureValue != null && !measureValue.isEmpty()){
                                            IngredientCocktail ingredientCocktail = new IngredientCocktail();
                                            IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
                                            ingredientCocktailId.setDrink(drink);
                                            ingredientCocktailId.setIngredient(ingredient);
                                            ingredientCocktail.setId(ingredientCocktailId);
                                            ingredientCocktail.setIngredient(ingredient);
                                            ingredientCocktail.setMeasure(measureValue);
                                            ingredientCocktailRepository.save(ingredientCocktail);
                                        }
                                    }
                                }

                            });




                    return drink;
                })
                .collect(Collectors.toList());
    }
}
