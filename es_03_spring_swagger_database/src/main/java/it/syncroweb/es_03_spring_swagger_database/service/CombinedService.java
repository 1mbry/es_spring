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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombinedService {

    private static final Logger logger = LoggerFactory.getLogger(CombinedService.class);

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
            logger.info("Inizio Combined Service getAll()");

            logger.info("Ricerca nel db di tutte le categorie");
            List<Category> categories = categoryRepository.findAll();
            logger.info("Ritorno di tutte le entity di category");
            logger.info("Inizio di categories.stream() con mapping di CategoryResponse");
            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(ConvertUtils::mapCategoryResponse)
                    .collect(Collectors.toList());
            logger.info("Ritorno di categoryResponse dopo aver settato i valori");

            logger.info("Ricerca nel db di tutti i glass");
            List<Glass> glasses = glassRepository.findAll();
            logger.info("Ritorno di tutte le entity di glass");
            logger.info("Inizio di glasses.stream() con mapping di GlassResponse");
            List<GlassResponse> glassResponses = glasses.stream()
                    .map(ConvertUtils::mapGlassResponse)
                    .collect(Collectors.toList());
            logger.info("Ritorno di glassResponse dopo aver settato i valori");

            logger.info("Ricerca nel db di tutti gli ingredient");
            List<Ingredient> ingredients = ingredientRepository.findAll();
            logger.info("Ritorno di tutte le entity di ingredient");
            logger.info("Inizio di ingredients.stream() con mapping di IngredientResponse");
            List<IngredientResponse> ingredientResponses = ingredients.stream()
                    .map(ConvertUtils::mapIngredientResponse)
                    .collect(Collectors.toList());
            logger.info("Ritorno di ingredientResponse dopo aver settato i valori");

            logger.info("Ricerca nel db di tutti le language");
            List<Language> languages = languageRepository.findAll();
            logger.info("Ritorno di tutte le entity di language");
            logger.info("Inizio di languages.stream() con mapping di LanguageResponse");
            List<LanguageResponse> languageResponses = languages.stream()
                    .map(ConvertUtils::mapLanguageResponse)
                    .collect(Collectors.toList());
            logger.info("Ritorno di languageResponse dopo aver settato i valori");

            CombinedResponse combinedResponse = new CombinedResponse();
            combinedResponse.setCategory(categoryResponses);
            combinedResponse.setGlass(glassResponses);
            combinedResponse.setIngredient(ingredientResponses);
            combinedResponse.setLanguages(languageResponses);

            logger.info("Ritorno di combinedResponse");
            return combinedResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}