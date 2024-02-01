package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
}
