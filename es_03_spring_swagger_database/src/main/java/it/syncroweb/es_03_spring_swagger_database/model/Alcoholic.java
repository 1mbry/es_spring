package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "alcoholic")
public class Alcoholic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean type;

    @OneToMany(mappedBy = "alcoholic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drink> drinks;

    public boolean getType() {
        return type;
    }

    @JsonManagedReference(value = "alcoholic-drink")
    public List<Drink> getDrinks() {
        return drinks;
    }

}
