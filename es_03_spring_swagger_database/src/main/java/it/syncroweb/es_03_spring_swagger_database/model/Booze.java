package it.syncroweb.es_03_spring_swagger_database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booze")
public class Booze {

    @EmbeddedId
    private BoozeId id;

    private String measure;

    @ManyToOne(fetch = FetchType.LAZY)
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    public Booze(BoozeId id, String measure, Drink drink, Ingredient ingredient) {
        this.id = id;
        this.measure = measure;
        this.drink = drink;
        this.ingredient = ingredient;
    }

    public Booze(){

    }

    public BoozeId getId() {
        return id;
    }

    public void setId(BoozeId id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
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
