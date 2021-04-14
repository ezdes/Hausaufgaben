package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "sport_type")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SportType {
    @Id
    @SequenceGenerator(name = "sport_id_sequence", sequenceName = "sport_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @Column(name = "name", nullable = false, length = 55)
    String name;

}
