package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public Category getCategory(String value){
        return categoryRepository.findByCategory(value);
    }


    //Get all category
    public ResponseEntity<List<Category>> getAllCategory(){
        try {
            List<Category> categories = new ArrayList<>();
            categories = categoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one category
    public ResponseEntity<Category> addCategory(Category category){
        try{
            Category category1 = categoryRepository.save(category);
            return new ResponseEntity<>(category1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Category>> addAllCategory(List<Category> categories) {
        try {
            List<Category> instructions1 = categoryRepository.saveAll(categories);
            return new ResponseEntity<>(instructions1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
