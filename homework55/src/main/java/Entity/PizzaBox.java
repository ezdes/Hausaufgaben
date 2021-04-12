package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "pizza_box")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PizzaBox {
    @Id
    @SequenceGenerator(name = "box_id_sequence", sequenceName = "box_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "box_id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @Column(name = "color", nullable = false)
    String color;
    @Column(name = "destination_address", nullable = false)
    String destinationAddress;
    @OneToOne
    @JoinColumn(name = "pizza_id", nullable = true)
    Pizza pizza;

    public PizzaBox(String color, String destinationAddress, Pizza pizza) {
        this.color = color;
        this.destinationAddress = destinationAddress;
        this.pizza = pizza;
    }
}
