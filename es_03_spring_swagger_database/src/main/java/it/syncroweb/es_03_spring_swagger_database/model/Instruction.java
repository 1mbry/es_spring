package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "instruction")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_drink")
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_language")
    private Language language;


    public Instruction(Integer id, String text, Drink drink, Language language) {
        this.id = id;
        this.text = text;
        this.drink = drink;
        this.language = language;
    }

    public Instruction(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonBackReference(value = "drink-instruction")
    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @JsonBackReference(value = "language-instruction")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
