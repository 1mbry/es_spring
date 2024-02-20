package it.syncroweb.es_03_spring_swagger_database.mapper;

import it.syncroweb.es_03_spring_swagger_database.dto.CategoryResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.GlassResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.LanguageResponse;
import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CombinedMapper {

    CombinedMapper INSTANCE = Mappers.getMapper(CombinedMapper.class);

    CategoryResponse toCategoryResponse(Category category);

    GlassResponse toGlassResponse(Glass glass);

    @Mapping(source = "name", target = "language")
    LanguageResponse toLanguageResponse(Language language);

    IngredientResponse toIngredientResponse(Ingredient ingredient);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

    List<GlassResponse> toGlassResponseList(List<Glass> glasses);

    List<LanguageResponse> toLanguageResponseList(List<Language> languages);

    List<IngredientResponse> toIngredientResponseList(List<Ingredient> ingredients);
}
