package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "championship")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Championship {
    @Id
    @SequenceGenerator(name = "championship_id_sequence", sequenceName = "championship_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "championship_id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    Country country;
    @Column(name = "name")
    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id", referencedColumnName = "id")
    SportType sportType;
}
