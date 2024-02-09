package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "glass")
public class Glass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500)
    private String name;

    @OneToMany(mappedBy = "glass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drink> drinks;

    public Glass(Integer id, String name, List<Drink> drinks) {
        this.id = id;
        this.name = name;
        this.drinks = drinks;
    }

    public Glass(){

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

    @JsonManagedReference(value = "glass-drink")
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
