package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
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

    @JsonBackReference(value = "drink-ingredientCocktail")
    public Drink getDrink() {
        return drink;
    }

    @JsonBackReference(value = "ingredient-ingredientCocktail")
    public Ingredient getIngredient() {
        return ingredient;
    }

}
