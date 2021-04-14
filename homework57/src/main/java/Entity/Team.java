package Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "team")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    @Id
    @SequenceGenerator(name = "team_id_sequence", sequenceName = "team_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_sequence")
    @Column(name = "id", updatable = false)
    Long id;
    @Column(name = "name", nullable = false, length = 55)
    String name;
    @Column(name = "logo", nullable = false)
    String logoAddress;
    @Column(name = "official_cite", nullable = false)
    String officialCite;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type", referencedColumnName = "id")
    SportType sportType;
}
