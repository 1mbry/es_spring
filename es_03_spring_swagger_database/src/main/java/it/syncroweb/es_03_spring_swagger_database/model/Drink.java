package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
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

    public Drink(Integer id, LocalDateTime date_modified, String name, String alternate_name, String url_thumb, String image_attribution, String image_source, String video, String tags, String iba, String creative_commons, List<IngredientCocktail> ingredientCocktails, List<Instruction> instructions, Alcoholic alcoholic, Category category, Glass glass) {
        this.id = id;
        this.date_modified = date_modified;
        this.name = name;
        this.alternate_name = alternate_name;
        this.url_thumb = url_thumb;
        this.image_attribution = image_attribution;
        this.image_source = image_source;
        this.video = video;
        this.tags = tags;
        this.iba = iba;
        this.creative_commons = creative_commons;
        this.ingredientCocktails = ingredientCocktails;
        this.instructions = instructions;
        this.alcoholic = alcoholic;
        this.category = category;
        this.glass = glass;
    }

    public Drink(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(LocalDateTime date_modified) {
        this.date_modified = date_modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternate_name() {
        return alternate_name;
    }

    public void setAlternate_name(String alternate_name) {
        this.alternate_name = alternate_name;
    }

    public String getUrl_thumb() {
        return url_thumb;
    }

    public void setUrl_thumb(String url_thumb) {
        this.url_thumb = url_thumb;
    }

    public String getImage_attribution() {
        return image_attribution;
    }

    public void setImage_attribution(String image_attribution) {
        this.image_attribution = image_attribution;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIba() {
        return iba;
    }

    public void setIba(String iba) {
        this.iba = iba;
    }

    public String getCreative_commons() {
        return creative_commons;
    }

    public void setCreative_commons(String creative_commons) {
        this.creative_commons = creative_commons;
    }

    @JsonManagedReference(value = "drink-ingredientCocktail")
    public List<IngredientCocktail> getIngredientCocktails() {
        return ingredientCocktails;
    }

    public void setIngredientCocktails(List<IngredientCocktail> ingredientCocktails) {
        this.ingredientCocktails = ingredientCocktails;
    }

    @JsonManagedReference(value = "drink-instruction")
    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @JsonBackReference(value = "alcoholic-drink")
    public Alcoholic getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(Alcoholic alcoholic) {
        this.alcoholic = alcoholic;
    }

    @JsonBackReference(value = "category-drink")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonBackReference(value = "glass-drink")
    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }
}

