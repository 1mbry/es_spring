package it.syncroweb.es_03_spring_swagger_database.model;

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
    private List<Booze> boozes;

    public Ingredient(Integer id, String name, List<Booze> boozes) {
        this.id = id;
        this.name = name;
        this.boozes = boozes;
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

    public List<Booze> getBoozes() {
        return boozes;
    }

    public void setBoozes(List<Booze> boozes) {
        this.boozes = boozes;
    }
}
