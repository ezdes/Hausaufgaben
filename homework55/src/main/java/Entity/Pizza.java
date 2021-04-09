package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "pizza")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pizza {
    @Id
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @Column(name = "name", length = 55, nullable = false)
    String name;
    @Column(name = "price", nullable = false)
    Double price;
    @Column(name = "weight", nullable = false)
    Double weight;

    public Pizza(String name, Double price, Double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
}
