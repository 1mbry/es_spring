package it.syncroweb.es_03_spring_swagger_database.model;

import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class BoozeId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_drink")
    private Drink drink;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_ingredient")
    private Ingredient ingredient;

    public BoozeId(Drink drink, Ingredient ingredient) {
        this.drink = drink;
        this.ingredient = ingredient;
    }
    public BoozeId(){

    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
