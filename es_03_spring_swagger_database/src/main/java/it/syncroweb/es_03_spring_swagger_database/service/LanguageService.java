package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import it.syncroweb.es_03_spring_swagger_database.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

    @Autowired
    public LanguageRepository languageRepository;

    //Get all language
    public ResponseEntity<List<Language>> getAllLanguage(){
        try {
            List<Language> languages = new ArrayList<>();
            languages = languageRepository.findAll();
            return new ResponseEntity<>(languages, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Language> addLanguage(Language language){
        try{
            Language language1 = languageRepository.save(language);
            return new ResponseEntity<>(language1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
