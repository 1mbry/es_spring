package it.syncroweb.es_03_spring_swagger_database.utils;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ConvertUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static List<DrinkResponse> mapDrinksResponse(List<Drink> drinks) {
        logger.info("Inizio di drinks.stream() con mapping di DrinkResponse");
        return drinks.stream()
                .map(ConvertUtils::mapDrinkResponse)
                .collect(Collectors.toList());
    }

    public static DrinkResponse mapDrinkResponse(Drink drink){
        logger.info("Inizio di drink.getIngredientCocktails().stream() con mapping di IngredientCocktailResponse");
        List<IngredientCocktailResponse> ingredientCocktailResponses = drink.getIngredientCocktails().stream()
                .map(ConvertUtils::mapIngredientCocktailResponse)
                .collect(Collectors.toList());
        logger.info("Ritorno di ingredientCocktailResponse dopo aver settato i valori");

        logger.info("Inizio di drink.getInstructions().stream() con mapping di InstructionResponse");
        List<InstructionResponse> instructionResponses = drink.getInstructions().stream()
                .map(ConvertUtils::mapInstructionResponse)
                .collect(Collectors.toList());
        logger.info("Ritorno di instructionResponse dopo aver settato i valori");

        Alcoholic alcoholic = drink.getAlcoholic();
        boolean alcoholicType = alcoholic.getType();

        Glass glass = drink.getGlass();
        String glassName = glass.getName();

        Category category = drink.getCategory();
        String categoryName = category.getName();

        logger.info("Ritorno di drinkResponse dopo aver settato i valori");
        return new DrinkResponse(drink.getId(), drink.getName(),drink.getAlternate_name(),alcoholicType,glassName,categoryName,drink.getUrl_thumb(),drink.getImage_attribution(),drink.getImage_source(),drink.getVideo(),drink.getTags(),drink.getIba(),drink.getCreative_commons(), ingredientCocktailResponses, instructionResponses);
    }

    public static IngredientCocktailResponse mapIngredientCocktailResponse(IngredientCocktail ingredientCocktail){
        IngredientCocktailResponse ingredientCocktailResponse = new IngredientCocktailResponse();
        ingredientCocktailResponse.setName(ingredientCocktail.getIngredient().getName());
        ingredientCocktailResponse.setMeasure(ingredientCocktail.getMeasure());
        return ingredientCocktailResponse;
    }

    public static InstructionResponse mapInstructionResponse(Instruction instruction){
        return new InstructionResponse(instruction.getLanguage().getName(), instruction.getText());
    }


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
        logger.info("Ritorno di drink dopo aver settato i valori");
        return drink;
    }

    public static List<Instruction> mapInstructionList(DrinkRequest drinkRequest, Drink drink, HashMap<String,Language> languageHashMap){
        logger.info("Inizio di drinkRequest.getInstructions().stream() con mapping di Instruction");
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


    public static List<IngredientCocktail> mapIngredientCocktailList(DrinkRequest drinkRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap){
        logger.info("Inizio di drinkRequest.getIngredients().stream() con mapping di IngredientCocktail");
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
    public static Instruction mapInstruction(InstructionRequest instructionRequest, Drink drink, HashMap<String,Language> languageHashMap) throws UnprocessableEntityException{
        Instruction instruction = new Instruction();
        instruction.setText(instructionRequest.getText());
        instruction.setDrink(drink);
        instruction.setLanguage(languageHashMap.get(instructionRequest.getLanguage()));
        logger.info("Ritorno di instruction dopo aver settato i valori");
        return instruction;
    }

    //

    public static IngredientCocktail mapIngredientCocktail(IngredientCocktailRequest ingredientCocktailRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap) throws UnprocessableEntityException{
        IngredientCocktail ingredientCocktail = new IngredientCocktail();
        IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
        ingredientCocktailId.setDrink(drink);
        ingredientCocktailId.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setId(ingredientCocktailId);
        ingredientCocktail.setMeasure(ingredientCocktailRequest.getMeasure());
        logger.info("Ritorno di ingredientCocktail dopo aver settato i valori");
        return ingredientCocktail;
    }

    public static IngredientResponse mapIngredientResponse(Ingredient ingredient){
        IngredientResponse ingredientResponse = new IngredientResponse();
        ingredientResponse.setId(ingredient.getId());
        ingredientResponse.setName(ingredient.getName());
        return ingredientResponse;
    }
    public static CategoryResponse mapCategoryResponse(Category category ){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    public static GlassResponse mapGlassResponse(Glass glass){
        GlassResponse glassResponse = new GlassResponse();
        glassResponse.setId(glass.getId());
        glassResponse.setName(glass.getName());
        return glassResponse;
    }

    public static LanguageResponse mapLanguageResponse(Language language){
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setId(language.getId());
        languageResponse.setLanguage(language.getName());
        return languageResponse;
    }
}
