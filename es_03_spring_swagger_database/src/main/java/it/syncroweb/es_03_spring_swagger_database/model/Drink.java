package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private LocalDateTime date_modified;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String alternate_name;

    @Column(length = 1000)
    private String url_thumb;

    @Column(length = 1000)
    private String image_attribution;

    @Column(length = 1000)
    private String image_source;

    @Column(length = 1000)
    private String video;

    @Column(length = 1000)
    private String tags;

    @Column(length = 500)
    private String iba;

    @Column(length = 500)
    private String creative_commons;

    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IngredientCocktail> ingredientCocktails;

    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Instruction> instructions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_alcoholic")
    @NotNull
    private Alcoholic alcoholic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_category")
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_glass")
    @NotNull
    private Glass glass;

    @JsonManagedReference(value = "drink-ingredientCocktail")
    public List<IngredientCocktail> getIngredientCocktails() {
        return ingredientCocktails;
    }

    @JsonManagedReference(value = "drink-instruction")
    public List<Instruction> getInstructions() {
        return instructions;
    }

    @JsonBackReference(value = "alcoholic-drink")
    public Alcoholic getAlcoholic() {
        return alcoholic;
    }

    @JsonBackReference(value = "category-drink")
    public Category getCategory() {
        return category;
    }

    @JsonBackReference(value = "glass-drink")
    public Glass getGlass() {
        return glass;
    }

}

