package it.syncroweb.es_03_spring_swagger_database.feign;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cocktailClient", url = "https://www.thecocktaildb.com")
public interface CocktailClient {

    @GetMapping(value ="/api/json/v1/1/search.php?f=a", headers = {"Content-Type=application/json; charset=UTF-8"})
    CocktailApiResponse getCocktail();

}
