package it.syncroweb.es_03_spring_swagger_database.mapper;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DrinkMapper {

    DrinkMapper INSTANCE = Mappers.getMapper(DrinkMapper.class);

    @Mapping(target = "alcoholic", expression = "java(drink.getAlcoholic().getType())")
    @Mapping(target = "glass", expression = "java(drink.getGlass().getName())")
    @Mapping(target = "category", expression = "java(drink.getCategory().getName())")
    @Mapping(target = "instructions")
    @Mapping(source = "ingredientCocktails", target = "ingredients")
    DrinkResponse mapDrink(Drink drink);

    List<DrinkResponse> mapDrinkList(List<Drink> drinks);

    @Mapping(target = "language", expression = "java(instruction.getLanguage().getName())")
    InstructionResponse mapInstruction(Instruction instruction);

    List<InstructionResponse> mapInstructionList(List<Instruction> instructions);

    @Mapping(target = "name", expression = "java(ingredientCocktail.getIngredient().getName())")
    IngredientCocktailResponse mapIngredientCocktail(IngredientCocktail ingredientCocktail);

    List<IngredientCocktailResponse>  mapIngredientCocktailList(List<IngredientCocktail> ingredientCocktails);
}
