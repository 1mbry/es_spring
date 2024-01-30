package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.request.CocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.model.Cocktail;
import it.syncroweb.es_03_spring_swagger_database.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CocktailService {

    @Autowired
    CocktailRepository cocktailRepository;

    //Get All Cocktails
    public ResponseEntity<List<Cocktail>> getAllCocktails(){
        try{
            List<Cocktail> cocktails = new ArrayList<Cocktail>();
            cocktails = cocktailRepository.findAll();
            return new ResponseEntity<>(cocktails, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    //Post a Cocktail
    public ResponseEntity<Cocktail> createCocktail(CocktailRequest cocktailRequest){
        try{
            Cocktail cocktail1 = new Cocktail();
            LocalDateTime dateModified = LocalDateTime.parse(cocktailRequest.getDateModified(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            cocktail1.setIdDrink(cocktailRequest.getIdDrink());
            cocktail1.setStrDrink(cocktailRequest.getStrDrink());
            cocktail1.setStrDrinkAlternate(cocktailRequest.getStrDrinkAlternate());
            cocktail1.setStrTags(cocktailRequest.getStrTags());
            cocktail1.setStrVideo(cocktailRequest.getStrVideo());
            cocktail1.setStrCategory(cocktailRequest.getStrCategory());
            cocktail1.setStrIBA(cocktailRequest.getStrIBA());
            cocktail1.setStrAlcoholic(cocktailRequest.getStrAlcoholic());
            cocktail1.setStrGlass(cocktailRequest.getStrGlass());
            cocktail1.setStrInstructions(cocktailRequest.getStrInstructions());
            cocktail1.setStrInstructionsES(cocktailRequest.getStrInstructionsES());
            cocktail1.setStrInstructionsDE(cocktailRequest.getStrInstructionsDE());
            cocktail1.setStrInstructionsFR(cocktailRequest.getStrInstructionsFR());
            cocktail1.setStrInstructionsIT(cocktailRequest.getStrInstructionsIT());
            cocktail1.setStrInstructionsZH_HANS(cocktailRequest.getStrInstructionsZH_HANS());
            cocktail1.setStrInstructionsZH_HANT(cocktailRequest.getStrInstructionsZH_HANT());
            cocktail1.setStrDrinkThumb(cocktailRequest.getStrDrinkThumb());
            cocktail1.setStrIngredient1(cocktailRequest.getStrIngredient1());
            cocktail1.setStrIngredient2(cocktailRequest.getStrIngredient2());
            cocktail1.setStrIngredient3(cocktailRequest.getStrIngredient3());
            cocktail1.setStrIngredient4(cocktailRequest.getStrIngredient4());
            cocktail1.setStrIngredient5(cocktailRequest.getStrIngredient5());
            cocktail1.setStrIngredient6(cocktailRequest.getStrIngredient6());
            cocktail1.setStrIngredient7(cocktailRequest.getStrIngredient7());
            cocktail1.setStrIngredient8(cocktailRequest.getStrIngredient8());
            cocktail1.setStrIngredient9(cocktailRequest.getStrIngredient9());
            cocktail1.setStrIngredient10(cocktailRequest.getStrIngredient10());
            cocktail1.setStrIngredient11(cocktailRequest.getStrIngredient11());
            cocktail1.setStrIngredient12(cocktailRequest.getStrIngredient12());
            cocktail1.setStrIngredient13(cocktailRequest.getStrIngredient13());
            cocktail1.setStrIngredient14(cocktailRequest.getStrIngredient14());
            cocktail1.setStrIngredient15(cocktailRequest.getStrIngredient15());
            cocktail1.setStrMeasure1(cocktailRequest.getStrMeasure1());
            cocktail1.setStrMeasure2(cocktailRequest.getStrMeasure2());
            cocktail1.setStrMeasure3(cocktailRequest.getStrMeasure3());
            cocktail1.setStrMeasure4(cocktailRequest.getStrMeasure4());
            cocktail1.setStrMeasure5(cocktailRequest.getStrMeasure5());
            cocktail1.setStrMeasure6(cocktailRequest.getStrMeasure6());
            cocktail1.setStrMeasure7(cocktailRequest.getStrMeasure7());
            cocktail1.setStrMeasure8(cocktailRequest.getStrMeasure8());
            cocktail1.setStrMeasure9(cocktailRequest.getStrMeasure9());
            cocktail1.setStrMeasure10(cocktailRequest.getStrMeasure10());
            cocktail1.setStrMeasure11(cocktailRequest.getStrMeasure11());
            cocktail1.setStrMeasure12(cocktailRequest.getStrMeasure12());
            cocktail1.setStrMeasure13(cocktailRequest.getStrMeasure13());
            cocktail1.setStrMeasure14(cocktailRequest.getStrMeasure14());
            cocktail1.setStrMeasure15(cocktailRequest.getStrMeasure15());
            cocktail1.setStrImageSource(cocktailRequest.getStrImageSource());
            cocktail1.setStrImageAttribution(cocktailRequest.getStrImageAttribution());
            cocktail1.setStrCreativeCommonsConfirmed(cocktailRequest.getStrCreativeCommonsConfirmed());
            cocktail1.setDateModified(dateModified);

            cocktailRepository.save(cocktail1);
            return new ResponseEntity<>(cocktail1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Cocktail>> createAllCocktails(List<CocktailRequest> cocktailRequest){
        try{
            List<Cocktail> cocktails = new ArrayList<>();

            for (CocktailRequest cocktailRequest1 : cocktailRequest) {
                Cocktail cocktail = new Cocktail();
                LocalDateTime dateModified = LocalDateTime.parse(cocktailRequest1.getDateModified(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                cocktail.setIdDrink(cocktailRequest1.getIdDrink());
                cocktail.setStrDrink(cocktailRequest1.getStrDrink());
                cocktail.setStrDrinkAlternate(cocktailRequest1.getStrDrinkAlternate());
                cocktail.setStrTags(cocktailRequest1.getStrTags());
                cocktail.setStrVideo(cocktailRequest1.getStrVideo());
                cocktail.setStrCategory(cocktailRequest1.getStrCategory());
                cocktail.setStrIBA(cocktailRequest1.getStrIBA());
                cocktail.setStrAlcoholic(cocktailRequest1.getStrAlcoholic());
                cocktail.setStrGlass(cocktailRequest1.getStrGlass());
                cocktail.setStrInstructions(cocktailRequest1.getStrInstructions());
                cocktail.setStrInstructionsES(cocktailRequest1.getStrInstructionsES());
                cocktail.setStrInstructionsDE(cocktailRequest1.getStrInstructionsDE());
                cocktail.setStrInstructionsFR(cocktailRequest1.getStrInstructionsFR());
                cocktail.setStrInstructionsIT(cocktailRequest1.getStrInstructionsIT());
                cocktail.setStrInstructionsZH_HANS(cocktailRequest1.getStrInstructionsZH_HANS());
                cocktail.setStrInstructionsZH_HANT(cocktailRequest1.getStrInstructionsZH_HANT());
                cocktail.setStrDrinkThumb(cocktailRequest1.getStrDrinkThumb());
                cocktail.setStrIngredient1(cocktailRequest1.getStrIngredient1());
                cocktail.setStrIngredient2(cocktailRequest1.getStrIngredient2());
                cocktail.setStrIngredient3(cocktailRequest1.getStrIngredient3());
                cocktail.setStrIngredient4(cocktailRequest1.getStrIngredient4());
                cocktail.setStrIngredient5(cocktailRequest1.getStrIngredient5());
                cocktail.setStrIngredient6(cocktailRequest1.getStrIngredient6());
                cocktail.setStrIngredient7(cocktailRequest1.getStrIngredient7());
                cocktail.setStrIngredient8(cocktailRequest1.getStrIngredient8());
                cocktail.setStrIngredient9(cocktailRequest1.getStrIngredient9());
                cocktail.setStrIngredient10(cocktailRequest1.getStrIngredient10());
                cocktail.setStrIngredient11(cocktailRequest1.getStrIngredient11());
                cocktail.setStrIngredient12(cocktailRequest1.getStrIngredient12());
                cocktail.setStrIngredient13(cocktailRequest1.getStrIngredient13());
                cocktail.setStrIngredient14(cocktailRequest1.getStrIngredient14());
                cocktail.setStrIngredient15(cocktailRequest1.getStrIngredient15());
                cocktail.setStrMeasure1(cocktailRequest1.getStrMeasure1());
                cocktail.setStrMeasure2(cocktailRequest1.getStrMeasure2());
                cocktail.setStrMeasure3(cocktailRequest1.getStrMeasure3());
                cocktail.setStrMeasure4(cocktailRequest1.getStrMeasure4());
                cocktail.setStrMeasure5(cocktailRequest1.getStrMeasure5());
                cocktail.setStrMeasure6(cocktailRequest1.getStrMeasure6());
                cocktail.setStrMeasure7(cocktailRequest1.getStrMeasure7());
                cocktail.setStrMeasure8(cocktailRequest1.getStrMeasure8());
                cocktail.setStrMeasure9(cocktailRequest1.getStrMeasure9());
                cocktail.setStrMeasure10(cocktailRequest1.getStrMeasure10());
                cocktail.setStrMeasure11(cocktailRequest1.getStrMeasure11());
                cocktail.setStrMeasure12(cocktailRequest1.getStrMeasure12());
                cocktail.setStrMeasure13(cocktailRequest1.getStrMeasure13());
                cocktail.setStrMeasure14(cocktailRequest1.getStrMeasure14());
                cocktail.setStrMeasure15(cocktailRequest1.getStrMeasure15());
                cocktail.setStrImageSource(cocktailRequest1.getStrImageSource());
                cocktail.setStrImageAttribution(cocktailRequest1.getStrImageAttribution());
                cocktail.setStrCreativeCommonsConfirmed(cocktailRequest1.getStrCreativeCommonsConfirmed());
                cocktail.setDateModified(dateModified);

                cocktails.add(cocktail);
            }
            cocktailRepository.saveAll(cocktails);
            return new ResponseEntity<>(cocktails, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Put
    public ResponseEntity<Cocktail> updateCocktail(Integer id, Cocktail cocktail){
        Optional<Cocktail> cocktailData = cocktailRepository.findById(id);
        if(cocktailData.isPresent()){
            Cocktail cocktail1 = cocktailData.get();
            cocktail1.setIdDrink(cocktail.getIdDrink());
            cocktail1.setStrDrink(cocktail.getStrDrink());
            cocktail1.setStrDrinkAlternate(cocktail.getStrDrinkAlternate());
            cocktail1.setStrTags(cocktail.getStrTags());
            cocktail1.setStrVideo(cocktail.getStrVideo());
            cocktail1.setStrCategory(cocktail.getStrCategory());
            cocktail1.setStrIBA(cocktail.getStrIBA());
            cocktail1.setStrAlcoholic(cocktail.getStrAlcoholic());
            cocktail1.setStrGlass(cocktail.getStrGlass());
            cocktail1.setStrInstructions(cocktail.getStrInstructions());
            cocktail1.setStrInstructionsES(cocktail.getStrInstructionsES());
            cocktail1.setStrInstructionsDE(cocktail.getStrInstructionsDE());
            cocktail1.setStrInstructionsFR(cocktail.getStrInstructionsFR());
            cocktail1.setStrInstructionsIT(cocktail.getStrInstructionsIT());
            cocktail1.setStrInstructionsZH_HANS(cocktail.getStrInstructionsZH_HANS());
            cocktail1.setStrInstructionsZH_HANT(cocktail.getStrInstructionsZH_HANT());
            cocktail1.setStrDrinkThumb(cocktail.getStrDrinkThumb());
            cocktail1.setStrIngredient1(cocktail.getStrIngredient1());
            cocktail1.setStrIngredient2(cocktail.getStrIngredient2());
            cocktail1.setStrIngredient3(cocktail.getStrIngredient3());
            cocktail1.setStrIngredient4(cocktail.getStrIngredient4());
            cocktail1.setStrIngredient5(cocktail.getStrIngredient5());
            cocktail1.setStrIngredient6(cocktail.getStrIngredient6());
            cocktail1.setStrIngredient7(cocktail.getStrIngredient7());
            cocktail1.setStrIngredient8(cocktail.getStrIngredient8());
            cocktail1.setStrIngredient9(cocktail.getStrIngredient9());
            cocktail1.setStrIngredient10(cocktail.getStrIngredient10());
            cocktail1.setStrIngredient11(cocktail.getStrIngredient11());
            cocktail1.setStrIngredient12(cocktail.getStrIngredient12());
            cocktail1.setStrIngredient13(cocktail.getStrIngredient13());
            cocktail1.setStrIngredient14(cocktail.getStrIngredient14());
            cocktail1.setStrIngredient15(cocktail.getStrIngredient15());
            cocktail1.setStrMeasure1(cocktail.getStrMeasure1());
            cocktail1.setStrMeasure2(cocktail.getStrMeasure2());
            cocktail1.setStrMeasure3(cocktail.getStrMeasure3());
            cocktail1.setStrMeasure4(cocktail.getStrMeasure4());
            cocktail1.setStrMeasure5(cocktail.getStrMeasure5());
            cocktail1.setStrMeasure6(cocktail.getStrMeasure6());
            cocktail1.setStrMeasure7(cocktail.getStrMeasure7());
            cocktail1.setStrMeasure8(cocktail.getStrMeasure8());
            cocktail1.setStrMeasure9(cocktail.getStrMeasure9());
            cocktail1.setStrMeasure10(cocktail.getStrMeasure10());
            cocktail1.setStrMeasure11(cocktail.getStrMeasure11());
            cocktail1.setStrMeasure12(cocktail.getStrMeasure12());
            cocktail1.setStrMeasure13(cocktail.getStrMeasure13());
            cocktail1.setStrMeasure14(cocktail.getStrMeasure14());
            cocktail1.setStrMeasure15(cocktail.getStrMeasure15());
            cocktail1.setStrImageSource(cocktail.getStrImageSource());
            cocktail1.setStrImageAttribution(cocktail.getStrImageAttribution());
            cocktail1.setStrCreativeCommonsConfirmed(cocktail.getStrCreativeCommonsConfirmed());
            cocktail1.setDateModified(cocktail.getDateModified());
            return new ResponseEntity<>(cocktailRepository.save(cocktail1),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
