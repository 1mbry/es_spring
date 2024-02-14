package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ingredient_cocktail")
public class IngredientCocktail {

    @EmbeddedId
    private IngredientCocktailId id;

    @Column
    private String measure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_drink", insertable = false, updatable = false)
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_ingredient", insertable = false, updatable = false)
    private Ingredient ingredient;

    public IngredientCocktail(IngredientCocktailId id, String measure, Drink drink, Ingredient ingredient) {
        this.id = id;
        this.measure = measure;
        this.drink = drink;
        this.ingredient = ingredient;
    }

    public IngredientCocktail(){

    }


    public IngredientCocktailId getId() {
        return id;
    }

    public void setId(IngredientCocktailId id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @JsonBackReference(value = "drink-ingredientCocktail")
    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @JsonBackReference(value = "ingredient-ingredientCocktail")
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
