package it.syncroweb.es_03_spring_swagger_database.utils;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertUtils {

    @Autowired
    public LanguageRepository languageRepository;

    @Autowired
    public IngredientRepository ingredientRepository;

    /*public DrinkRequestDTO convertDrinkDTOToEntity(DrinkRequestDTO drinkRequestDTO){
        Drink drink = new Drink();
        drink.setName(drinkRequestDTO.getName());
        drink.setAlternate_name(drinkRequestDTO.getAlternate_name());

        Alcoholic alcoholic = alcoholicRepository.findByValue(drinkRequestDTO.isAlcoholic());
        drink.setAlcoholic(alcoholic);
    }*/
    public static List<DrinkResponseDTO> convertDrinksToDTOs(List<Drink> drinks) {
        return drinks.stream()
                .map(ConvertUtils::convertDrinkToDTO)
                .collect(Collectors.toList());
    }

    public static DrinkResponseDTO convertDrinkToDTO(Drink drink){
        List<IngredientCocktailDTO> ingredientCocktailDTOS = drink.getIngredientCocktails().stream()
                .map(ConvertUtils::convertIngredientsToDTO)
                .collect(Collectors.toList());

        List<InstructionResponseDTO> instructionResponseDTOS = drink.getInstructions().stream()
                .map(ConvertUtils::convertInstructionToDTO)
                .collect(Collectors.toList());

        Alcoholic alcoholic = drink.getAlcoholic();
        boolean alcoholicType = alcoholic.getType();

        Glass glass = drink.getGlass();
        String glassName = glass.getName();

        Category category = drink.getCategory();
        String categoryName = category.getName();

        return new DrinkResponseDTO(drink.getId(), drink.getName(),drink.getAlternate_name(),alcoholicType,glassName,categoryName,drink.getUrl_thumb(),drink.getImage_attribution(),drink.getImage_source(),drink.getVideo(),drink.getTags(),drink.getIba(),drink.getCreative_commons(), ingredientCocktailDTOS, instructionResponseDTOS);
    }



    public static  IngredientCocktailDTO convertIngredientsToDTO(IngredientCocktail ingredientCocktail){
        IngredientCocktailDTO ingredientCocktailDTO = new IngredientCocktailDTO();
        ingredientCocktailDTO.setName(ingredientCocktail.getIngredient().getName());
        ingredientCocktailDTO.setMeasure(ingredientCocktail.getMeasure());
        return ingredientCocktailDTO;
    }

    public static InstructionResponseDTO convertInstructionToDTO(Instruction instruction){
        return new InstructionResponseDTO(instruction.getLanguage().getName(), instruction.getText());
    }


    public static Drink convertDrinkRequestDTOToEntity(DrinkRequestDTO drinkRequestDTO, Alcoholic alcoholic, Category category, Glass glass){
        Drink drink = new Drink();
        drink.setName(drinkRequestDTO.getName());
        drink.setAlternate_name(drinkRequestDTO.getAlternate_name());
        drink.setAlcoholic(alcoholic);
        drink.setCategory(category);
        drink.setGlass(glass);
        drink.setUrl_thumb(drinkRequestDTO.getUrl_thumb());
        drink.setImage_attribution(drinkRequestDTO.getImage_attribution());
        drink.setImage_source(drinkRequestDTO.getImage_source());
        drink.setVideo(drinkRequestDTO.getVideo());
        drink.setTags(drinkRequestDTO.getTags());
        drink.setIba(drinkRequestDTO.getIba());
        drink.setCreative_commons(drinkRequestDTO.getCreative_commons());
        return drink;
    }


    public  List<Instruction> converter(DrinkRequestDTO drinkRequestDTO, Drink drink){
        List<Instruction> instructions = drinkRequestDTO.getInstruction().stream()
                .map(instructionRequestDTO -> convertInstructionRequestDTOToEntity(instructionRequestDTO, drink))
                .collect(Collectors.toList());
        return instructions;
    }
    public Instruction convertInstructionRequestDTOToEntity(InstructionRequestDTO instructionRequestDTO, Drink drink){
        Instruction instruction = new Instruction();
        instruction.setText(instructionRequestDTO.getText());
        instruction.setDrink(drink);
        Language language = languageRepository.findLanguage(instructionRequestDTO.getLanguage());
        if(language == null){
            throw new IllegalArgumentException("Language not found");
        }else {
            instruction.setLanguage(language);
        }
        return instruction;
    }

    public  List<IngredientCocktail> converters(DrinkRequestDTO drinkRequestDTO, Drink drink){
        List<IngredientCocktail> ingredientCocktails = drinkRequestDTO.getIngredients().stream()
                .map(ingredientCocktailRequestDTO -> convertIngredientCocktailRequestDTOToEntity(ingredientCocktailRequestDTO,  drink))
                .collect(Collectors.toList());

        return ingredientCocktails;
    }


    public  IngredientCocktail convertIngredientCocktailRequestDTOToEntity(IngredientCocktailRequestDTO ingredientCocktailRequestDTO, Drink drink){
        IngredientCocktail ingredientCocktail = new IngredientCocktail();
        IngredientCocktailId ingredientCocktailId = new IngredientCocktailId();
        ingredientCocktailId.setDrink(drink);

        Ingredient ingredient = ingredientRepository.findIngredient(ingredientCocktailRequestDTO.getName());
        if(ingredient == null){
            throw new IllegalArgumentException("Ingredient not found");
        }else {
            ingredientCocktailId.setIngredient(ingredient);
        }
        ingredientCocktail.setIngredient(ingredient);
        ingredientCocktail.setId(ingredientCocktailId);
        ingredientCocktail.setMeasure(ingredientCocktailRequestDTO.getMeasure());

        return ingredientCocktail;
    }



}
