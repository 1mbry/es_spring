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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<CombinedResponseDTO> getAll(){
        try {
            List<Category> categories = categoryRepository.findAll();
            List<CategoryResponseDTO> categoryResponseDTOS = categories.stream()
                    .map(this::convertCategoryToDTO)
                    .collect(Collectors.toList());

            List<Glass> glasses = glassRepository.findAll();
            List<GlassResponseDTO> glassResponseDTOS = glasses.stream()
                    .map(this::convertGlassToDTO)
                    .collect(Collectors.toList());

            List<Ingredient> ingredients = ingredientRepository.findAll();
            List<IngredientResponseDTO> ingredientResponseDTOS = ingredients.stream()
                    .map(this::convertIngredientToDTO)
                    .collect(Collectors.toList());

            List<Language> languages = languageRepository.findAll();
            List<LanguageResponseDTO> languageResponseDTOS = languages.stream()
                    .map(this::convertLanguageToDTO)
                    .collect(Collectors.toList());

            CombinedResponseDTO combinedResponseDTO = new CombinedResponseDTO();
            combinedResponseDTO.setCategory(categoryResponseDTOS);
            combinedResponseDTO.setGlass(glassResponseDTOS);
            combinedResponseDTO.setIngredient(ingredientResponseDTOS);
            combinedResponseDTO.setLanguages(languageResponseDTOS);

            return new ResponseEntity<>(combinedResponseDTO, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private IngredientResponseDTO convertIngredientToDTO(Ingredient ingredient){
        IngredientResponseDTO ingredientResponseDTO = new IngredientResponseDTO();
        ingredientResponseDTO.setId(ingredient.getId());
        ingredientResponseDTO.setName(ingredient.getName());
        return ingredientResponseDTO;
    }
    private CategoryResponseDTO convertCategoryToDTO(Category category ){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        return categoryResponseDTO;
    }
    private GlassResponseDTO convertGlassToDTO(Glass glass){
        GlassResponseDTO glassResponseDTO = new GlassResponseDTO();
        glassResponseDTO.setId(glass.getId());
        glassResponseDTO.setName(glass.getName());
        return glassResponseDTO;
    }

    private LanguageResponseDTO convertLanguageToDTO(Language language){
        LanguageResponseDTO languageResponseDTO = new LanguageResponseDTO();
        languageResponseDTO.setId(language.getId());
        languageResponseDTO.setLanguage(language.getName());
        return languageResponseDTO;
    }
}
