package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class IngredientCocktailId implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_drink")
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_ingredient")
    private Ingredient ingredient;

    public IngredientCocktailId(Drink drink, Ingredient ingredient) {
        this.drink = drink;
        this.ingredient = ingredient;
    }
    public IngredientCocktailId(){

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
