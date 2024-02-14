package it.syncroweb.es_03_spring_swagger_database.utils;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ConvertUtils {

    public static List<DrinkResponse> mapDrinksResponse(List<Drink> drinks) {
        return drinks.stream()
                .map(ConvertUtils::mapDrinkResponse)
                .collect(Collectors.toList());
    }

    public static DrinkResponse mapDrinkResponse(Drink drink){
        List<IngredientCocktailResponse> ingredientCocktailResponses = drink.getIngredientCocktails().stream()
                .map(ConvertUtils::convertIngredientsToDTO)
                .collect(Collectors.toList());

        List<InstructionResponse> instructionResponses = drink.getInstructions().stream()
                .map(ConvertUtils::convertInstructionToDTO)
                .collect(Collectors.toList());

        Alcoholic alcoholic = drink.getAlcoholic();
        boolean alcoholicType = alcoholic.getType();

        Glass glass = drink.getGlass();
        String glassName = glass.getName();

        Category category = drink.getCategory();
        String categoryName = category.getName();

        return new DrinkResponse(drink.getId(), drink.getName(),drink.getAlternate_name(),alcoholicType,glassName,categoryName,drink.getUrl_thumb(),drink.getImage_attribution(),drink.getImage_source(),drink.getVideo(),drink.getTags(),drink.getIba(),drink.getCreative_commons(), ingredientCocktailResponses, instructionResponses);
    }

    public static IngredientCocktailResponse convertIngredientsToDTO(IngredientCocktail ingredientCocktail){
        IngredientCocktailResponse ingredientCocktailResponse = new IngredientCocktailResponse();
        ingredientCocktailResponse.setName(ingredientCocktail.getIngredient().getName());
        ingredientCocktailResponse.setMeasure(ingredientCocktail.getMeasure());
        return ingredientCocktailResponse;
    }

    public static InstructionResponse convertInstructionToDTO(Instruction instruction){
        return new InstructionResponse(instruction.getLanguage().getName(), instruction.getText());
    }


    public static Drink convertDrinkRequestDTOToEntity(DrinkRequest drinkRequest, Alcoholic alcoholic, Category category, Glass glass){
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
        return drink;
    }

    public static List<Instruction> converter(DrinkRequest drinkRequest, Drink drink, HashMap<String,Language> languageHashMap){
        return drinkRequest.getInstruction().stream()
                .map(instructionRequest -> {
                    try {
                        return convertInstructionRequestDTOToEntity(instructionRequest, drink, languageHashMap);
                    } catch (UnprocessableEntityException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }


    public static List<IngredientCocktail> converters(DrinkRequest drinkRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap){
        return drinkRequest.getIngredients().stream()
                .map(ingredientCocktailRequest -> {
                    try {
                        return convertIngredientCocktailRequestDTOToEntity(ingredientCocktailRequest,  drink, ingredientHashMap);
                    } catch (UnprocessableEntityException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
    public static Instruction convertInstructionRequestDTOToEntity(InstructionRequest instructionRequest, Drink drink, HashMap<String,Language> languageHashMap) throws UnprocessableEntityException{
        Instruction instruction = new Instruction();
        instruction.setText(instructionRequest.getText());
        instruction.setDrink(drink);
        instruction.setLanguage(languageHashMap.get(instructionRequest.getLanguage()));

        return instruction;
    }

    //

    public static IngredientCocktail convertIngredientCocktailRequestDTOToEntity(IngredientCocktailRequest ingredientCocktailRequest, Drink drink, HashMap<String,Ingredient> ingredientHashMap) throws UnprocessableEntityException{
        IngredientCocktail ingredientCocktail = new IngredientCocktail();
        IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
        ingredientCocktailId.setDrink(drink);
        ingredientCocktailId.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setIngredient(ingredientHashMap.get(ingredientCocktailRequest.getName()));
        ingredientCocktail.setId(ingredientCocktailId);
        ingredientCocktail.setMeasure(ingredientCocktailRequest.getMeasure());

        return ingredientCocktail;
    }

    public static IngredientResponse convertIngredientToDTO(Ingredient ingredient){
        IngredientResponse ingredientResponse = new IngredientResponse();
        ingredientResponse.setId(ingredient.getId());
        ingredientResponse.setName(ingredient.getName());
        return ingredientResponse;
    }
    public static CategoryResponse convertCategoryToDTO(Category category ){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    public static GlassResponse convertGlassToDTO(Glass glass){
        GlassResponse glassResponse = new GlassResponse();
        glassResponse.setId(glass.getId());
        glassResponse.setName(glass.getName());
        return glassResponse;
    }

    public static LanguageResponse convertLanguageToDTO(Language language){
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setId(language.getId());
        languageResponse.setLanguage(language.getName());
        return languageResponse;
    }
}
