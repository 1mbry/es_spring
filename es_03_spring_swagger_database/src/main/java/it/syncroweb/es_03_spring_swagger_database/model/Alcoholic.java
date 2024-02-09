package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "alcoholic")
public class Alcoholic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean type;

    @OneToMany(mappedBy = "alcoholic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drink> drinks;

    public Alcoholic(Integer id, boolean type, List<Drink> drinks) {
        this.id = id;
        this.type = type;
        this.drinks = drinks;
    }

    public Alcoholic(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @JsonManagedReference(value = "alcoholic-drink")
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
