package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import it.syncroweb.es_03_spring_swagger_database.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/all")
    public ResponseEntity<List<Language>> getAllLanguage(){
        return languageService.getAllLanguage();
    }

    @PostMapping("")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language){
     return languageService.addLanguage(language);
    }
}
