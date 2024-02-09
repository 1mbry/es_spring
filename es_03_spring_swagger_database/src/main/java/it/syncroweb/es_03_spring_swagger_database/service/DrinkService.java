package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponseDTO;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequestDTO;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.*;
import it.syncroweb.es_03_spring_swagger_database.repository.*;
import it.syncroweb.es_03_spring_swagger_database.utils.ConvertUtils;
import it.syncroweb.es_03_spring_swagger_database.utils.FormatLogger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private static final FormatLogger logger = new FormatLogger(LogManager.getLogger(DrinkService.class));

    @Autowired
    public DrinkRepository drinkRepository;

    @Autowired
    public IngredientCocktailRepository ingredientCocktailRepository;

    @Autowired
    public InstructionRepository instructionRepository;

    @Autowired
    public AlcoholicRepository alcoholicRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public GlassRepository glassRepository;

    @Autowired
    public ConvertUtils utils;


    //Get all drink
    public ResponseEntity<List<DrinkResponseDTO>> getAllDrink(){
        try {
            List<Drink> drinks = drinkRepository.findAll();
            List<DrinkResponseDTO> drinkResponseDTOS = ConvertUtils.convertDrinksToDTOs(drinks);
            return new ResponseEntity<>(drinkResponseDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Post create one drink
    public DrinkResponseDTO addDrink(DrinkRequestDTO drinkRequestDTO) throws UnprocessableEntityException {
            //Ricavo Alcoholic dal database usando il valore booleano
            Alcoholic alcoholic = alcoholicRepository.findByValue(drinkRequestDTO.isAlcoholic());
            if(alcoholic == null){
                throw new UnprocessableEntityException("Alcoholic not found");
            }
            //Ricavo Category dal database usando il valore string
            Category category = categoryRepository.findByCategory(drinkRequestDTO.getCategory());
            if(category == null){
                throw new UnprocessableEntityException("Category not found");
            }

            //Ricavo Glass dal database usando il valore string
            Glass glass = glassRepository.findByGlass(drinkRequestDTO.getGlass());
            if(glass == null){
                throw new UnprocessableEntityException("Glass not found");
            }


            Drink drink = ConvertUtils.convertDrinkRequestDTOToEntity(drinkRequestDTO,alcoholic,category,glass);
            drinkRepository.save(drink);

            List<Instruction> instructions = utils.converter(drinkRequestDTO, drink);
            instructionRepository.saveAll(instructions);
            drink.setInstructions(instructions);

            List<IngredientCocktail> ingredientCocktails = utils.converters(drinkRequestDTO,drink);
            ingredientCocktailRepository.saveAll(ingredientCocktails);
            drink.setIngredientCocktails(ingredientCocktails);

            //logger.info("cocktail nome: %s", drink.getName());
            DrinkResponseDTO drinkResponseDTO = ConvertUtils.convertDrinkToDTO(drink);
            return drinkResponseDTO;
    }

    public ResponseEntity<DrinkResponseDTO> getDrink(){
        Drink drink = drinkRepository.findLastDrink();
        DrinkResponseDTO drinkResponseDTO = ConvertUtils.convertDrinkToDTO(drink);
        return new ResponseEntity<>(drinkResponseDTO, HttpStatus.OK);
    }

    //aggiungo i drink manualmente
    public ResponseEntity<List<Drink>> addAllDrink(List<Drink> drinks) {
        try {
            List<Drink> drinks1 = drinkRepository.saveAll(drinks);
            return new ResponseEntity<>(drinks1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
