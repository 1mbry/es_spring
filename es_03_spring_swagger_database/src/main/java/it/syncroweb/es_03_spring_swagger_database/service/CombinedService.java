package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.*;
import it.syncroweb.es_03_spring_swagger_database.mapper.CombinedMapper;
import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import it.syncroweb.es_03_spring_swagger_database.repository.CategoryRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.GlassRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.IngredientRepository;
import it.syncroweb.es_03_spring_swagger_database.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedService {

    private static final Logger log = LoggerFactory.getLogger(CombinedService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GlassRepository glassRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public CombinedResponse getAll() {
        try {
            log.info("Inizio combineService.getAll()");

            log.info("Ricerca nel db di tutte le categorie");
            List<Category> categories = categoryRepository.findAll();
            log.info("Ritorno del numero di categorie: {}", categories.size());
            log.info("Inizio mapping di CategoryResponse");
            /*List<CategoryResponse> categoryResponses = categories.stream()
                    .map(ConvertUtils::mapCategoryResponse)
                    .collect(Collectors.toList());*/
            List<CategoryResponse> categoryResponses = CombinedMapper.INSTANCE.toCategoryResponseList(categories);
            log.info("Ritorno di categoryResponse dopo aver settato i valori : {}", categoryResponses);

            log.info("Ricerca nel db di tutti i glass");
            List<Glass> glasses = glassRepository.findAll();
            log.info("Ritorno del numero di bicchieri: {}", glasses.size());
            log.info("Inizio mapping di GlassResponse");
            /*List<GlassResponse> glassResponses = glasses.stream()
                    .map(ConvertUtils::mapGlassResponse)
                    .collect(Collectors.toList());*/
            List<GlassResponse> glassResponses = CombinedMapper.INSTANCE.toGlassResponseList(glasses);
            log.info("Ritorno di glassResponse dopo aver settato i valori : {}", glassResponses);

            log.info("Ricerca nel db di tutti gli ingredienti");
            List<Ingredient> ingredients = ingredientRepository.findAll();
            log.info("Ritorno del numero di ingredienti: {}", ingredients.size());
            log.info("Inizio mapping di IngredientResponse");
            /*List<IngredientResponse> ingredientResponses = ingredients.stream()
                    .map(ConvertUtils::mapIngredientResponse)
                    .collect(Collectors.toList());*/
            List<IngredientResponse> ingredientResponses = CombinedMapper.INSTANCE.toIngredientResponseList(ingredients);
            log.info("Ritorno di ingredientResponse dopo aver settato i valori : {}", ingredientResponses);

            log.info("Ricerca nel db di tutti le lingue");
            List<Language> languages = languageRepository.findAll();
            log.info("Ritorno del numero di lingue: {}", languages.size());
            log.info("Inizio mapping di LanguageResponse");
            /*List<LanguageResponse> languageResponses = languages.stream()
                    .map(ConvertUtils::mapLanguageResponse)
                    .collect(Collectors.toList());*/
            List<LanguageResponse> languageResponses = CombinedMapper.INSTANCE.toLanguageResponseList(languages);
            log.info("Ritorno di languageResponse dopo aver settato i valori : {}", languageResponses);

            CombinedResponse combinedResponse = new CombinedResponse();
            combinedResponse.setCategory(categoryResponses);
            combinedResponse.setGlass(glassResponses);
            combinedResponse.setIngredient(ingredientResponses);
            combinedResponse.setLanguages(languageResponses);

            log.info("Ritorno di combinedResponse : {}", combinedResponse);
            return combinedResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}