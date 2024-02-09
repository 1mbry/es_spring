package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500)
    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IngredientCocktail> ingredientCocktails;

    public Ingredient(Integer id, String name, List<IngredientCocktail> ingredientCocktails) {
        this.id = id;
        this.name = name;
        this.ingredientCocktails = ingredientCocktails;
    }
    public Ingredient(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference(value = "ingredient-ingredientCocktail")
    public List<IngredientCocktail> getIngredientCocktails() {
        return ingredientCocktails;
    }

    public void setIngredientCocktails(List<IngredientCocktail> ingredientCocktails) {
        this.ingredientCocktails = ingredientCocktails;
    }
}
