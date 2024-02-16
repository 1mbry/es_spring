package it.syncroweb.es_03_spring_swagger_database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonManagedReference(value = "glass-drink")
    public List<Drink> getDrinks() {
        return drinks;
    }

}
