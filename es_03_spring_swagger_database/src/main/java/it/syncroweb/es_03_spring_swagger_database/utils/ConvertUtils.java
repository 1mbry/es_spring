package it.syncroweb.es_03_spring_swagger_database.utils;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionRequest;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ConvertUtils {
    private static final Logger log = LoggerFactory.getLogger(ConvertUtils.class);

    /*public static List<DrinkResponse> mapDrinksResponse(List<Drink> drinks) {
        log.info("Inizio di del processo con mapping di List<DrinkResponse> {}", drinks.size());
        return drinks.stream()
                .map(ConvertUtils::mapDrinkResponse)
                .collect(Collectors.toList());
    }*/

    /*public static DrinkResponse mapDrinkResponse(Drink drink){
        log.info("Inizio del processo con mapping di IngredientCocktailResponse con drink.id {}", drink.getId());
        List<IngredientCocktailResponse> ingredientCocktailResponses = drink.getIngredientCocktails().stream()
                .map(ConvertUtils::mapIngredientCocktailResponse)
                .collect(Collectors.toList());

        log.info("Inizio del processo con mapping di InstructionResponse {}", drink.getId());
        List<InstructionResponse> instructionResponses = drink.getInstructions().stream()
                .map(ConvertUtils::mapInstructionResponse)
                .collect(Collectors.toList());

        Alcoholic alcoholic = drink.getAlcoholic();
        boolean alcoholicType = alcoholic.getType();

        Glass glass = drink.getGlass();
        String glassName = glass.getName();

        Category category = drink.getCategory();
        String categoryName = category.getName();

        DrinkResponse drinkResponse = new DrinkResponse();
        drinkResponse.setId(drink.getId());
        drinkResponse.setName(drink.getName());
        drinkResponse.setAlternate_name(drink.getAlternate_name());
        drinkResponse.setAlcoholic(alcoholicType);
        drinkResponse.setGlass(glassName);
        drinkResponse.setCategory(categoryName);
        drinkResponse.setUrl_thumb(drink.getUrl_thumb());
        drinkResponse.setImage_attribution(drink.getImage_attribution());
        drinkResponse.setImage_source(drink.getImage_source());
        drinkResponse.setVideo(drink.getVideo());
        drinkResponse.setTags(drink.getTags());
        drinkResponse.setIba(drink.getIba());
        drinkResponse.setCreative_commons(drink.getCreative_commons());
        drinkResponse.setIngredients(ingredientCocktailResponses);
        drinkResponse.setInstructions(instructionResponses);
        log.info("Ritorno di drinkResponse dopo aver settato i valori {}", drinkResponse);
        return drinkResponse;
    }*/

    /*public static IngredientCocktailResponse mapIngredientCocktailResponse(IngredientCocktail ingredientCocktail){
        log.info("Sta processando IngredientCocktail con 'fk_id_drink {}' e 'fk_id_ingredient {}' ", ingredientCocktail.getId().getDrink().getId(), ingredientCocktail.getId().getIngredient().getId());
        IngredientCocktailResponse ingredientCocktailResponse = new IngredientCocktailResponse();
        ingredientCocktailResponse.setName(ingredientCocktail.getIngredient().getName());
        ingredientCocktailResponse.setMeasure(ingredientCocktail.getMeasure());
        log.info("Sta ritornando IngredientCocktailResponse {}", ingredientCocktailResponse);
        return ingredientCocktailResponse;
    }*/

    /*public static InstructionResponse mapInstructionResponse(Instruction instruction){
        logger.info("Sta processando Instruction", instruction);
        InstructionResponse instructionResponse = new InstructionResponse();
        instructionResponse.setLanguage(instruction.getLanguage().getName());
        instructionResponse.setText(instruction.getText());
        logger.info("Sta ritornando InstructionResponse", instructionResponse);
        return instructionResponse;
    }*/

    public static Drink mapDrink(DrinkRequest drinkRequest, Alcoholic alcoholic, Category category, Glass glass){
        Drink drink = new Drink();
        drink.setName(drinkRequest.getName());
        drink.setAlternate_name(drinkRequest.getAlternate_name());
        drink.setAlcoholic(alcoholic);
        drink.setCategory(category);
        drink.setGlass(glass);
        drink.setUrl_thumb(drinkRequest.getUrl_thumb());
        drink.setImage_attribution(drinkRequest.getImage_attribution());
        drink.setImage_source(drinkRequest.getImage_source());
        drink.setVideo(drinkRequest.getVideo());
        drink.setTags(drinkRequest.getTags());
        drink.setIba(drinkRequest.getIba());
        drink.setCreative_commons(drinkRequest.getCreative_commons());
        log.info("Ritorno di drink dopo aver settato i valori : {}", drink);
        return drink;
    }

    public static List<Instruction> mapInstructionList(DrinkRequest drinkRequest, Drink drink, HashMap<String,Language> languageHashMap){
        log.info("Inizio di mapping di Instruction");
        return drinkRequest.getInstructions().stream()
                .map(instructionRequest -> {
                    try {
                        return mapInstruction(instructionRequest, drink, languageHashMap);
                    } catch (UnprocessableEntityException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public static Instruction mapInstruction(InstructionRequest instructionRequest, Drink drink, HashMap<String,Language> languageHashMap) throws UnprocessableEntityException{
        Instruction instruction = new Instruction();
        instruction.setText(instructionRequest.getText());
        instruction.setDrink(drink);
        instruction.setLanguage(languageHashMap.get(instructionRequest.getLanguage()));
        log.info("Ritorno di instruction dopo aver settato i valori : {}", instruction);
        return instruction;
    }

    public static List<IngredientCocktail> mapIngredientCocktailList(DrinkRequest drinkRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap){
        log.info("Inizio mapping di IngredientCocktail");
        return drinkRequest.getIngredients().stream()
                .map(ingredientCocktailRequest -> {
                    try {
                        return mapIngredientCocktail(ingredientCocktailRequest,  drink, ingredientHashMap);
                    } catch (UnprocessableEntityException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public static IngredientCocktail mapIngredientCocktail(IngredientCocktailRequest ingredientCocktailRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap) throws UnprocessableEntityException{
        IngredientCocktail ingredientCocktail = new IngredientCocktail();
        IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
        ingredientCocktailId.setDrink(drink);
        ingredientCocktailId.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setId(ingredientCocktailId);
        ingredientCocktail.setMeasure(ingredientCocktailRequest.getMeasure());
        log.info("Ritorno di ingredientCocktail dopo aver settato i valori : {}", ingredientCocktail);
        return ingredientCocktail;
    }

    /*public static IngredientResponse mapIngredientResponse(Ingredient ingredient){
        IngredientResponse ingredientResponse = new IngredientResponse();
        ingredientResponse.setId(ingredient.getId());
        ingredientResponse.setName(ingredient.getName());
        return ingredientResponse;
    }*/

    /*public static CategoryResponse mapCategoryResponse(Category category ){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }*/

    /*public static GlassResponse mapGlassResponse(Glass glass){
        GlassResponse glassResponse = new GlassResponse();
        glassResponse.setId(glass.getId());
        glassResponse.setName(glass.getName());
        return glassResponse;
    }*/

    /*public static LanguageResponse mapLanguageResponse(Language language){
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setId(language.getId());
        languageResponse.setLanguage(language.getName());
        return languageResponse;
    }*/
}
