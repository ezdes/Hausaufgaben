package entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "follower")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User userFollower;
    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private User userFollowed;
}
