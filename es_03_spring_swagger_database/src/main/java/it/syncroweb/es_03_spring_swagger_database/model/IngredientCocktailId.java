package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class IngredientCocktailId implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_drink")
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_ingredient")
    private Ingredient ingredient;

    @JsonBackReference(value = "drink-ingredientCocktail")
    public Drink getDrink() {
        return drink;
    }

    @JsonBackReference(value = "ingredient-ingredientCocktail")
    public Ingredient getIngredient() {
        return ingredient;
    }

}
