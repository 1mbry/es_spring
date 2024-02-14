package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import it.syncroweb.es_03_spring_swagger_database.repository.CategoryRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.GlassRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.IngredientRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.LanguageRepository;
import it.syncroweb.es_03_spring_swagger_database.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombinedService {

    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public GlassRepository glassRepository;
    @Autowired
    public IngredientRepository ingredientRepository;
    @Autowired
    public LanguageRepository languageRepository;

    public CombinedResponse getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(ConvertUtils::convertCategoryToDTO)
                .collect(Collectors.toList());

        List<Glass> glasses = glassRepository.findAll();
        List<GlassResponse> glassResponses = glasses.stream()
                .map(ConvertUtils::convertGlassToDTO)
                .collect(Collectors.toList());

        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientResponse> ingredientResponses = ingredients.stream()
                .map(ConvertUtils::convertIngredientToDTO)
                .collect(Collectors.toList());

        List<Language> languages = languageRepository.findAll();
        List<LanguageResponse> languageResponses = languages.stream()
                .map(ConvertUtils::convertLanguageToDTO)
                .collect(Collectors.toList());

        CombinedResponse combinedResponse = new CombinedResponse();
        combinedResponse.setCategory(categoryResponses);
        combinedResponse.setGlass(glassResponses);
        combinedResponse.setIngredient(ingredientResponses);
        combinedResponse.setLanguages(languageResponses);

        return combinedResponse;
    }

}
