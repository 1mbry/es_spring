package it.syncroweb.es_03_spring_swagger_database.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cocktailClient", url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a")
public interface CocktailClient {

}
