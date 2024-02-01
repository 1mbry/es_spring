package it.syncroweb.es_03_spring_swagger_database.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "alcoholic")
public class Alcoholic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 500)
    private String type;

    @OneToMany(mappedBy = "alcoholic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drink> drinks;

    public Alcoholic(Integer id, String type, List<Drink> drinks) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
