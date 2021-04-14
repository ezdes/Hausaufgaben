package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "country")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {
    @Id
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @Column(name = "name", nullable = false, length = 55)
    String name;
}
