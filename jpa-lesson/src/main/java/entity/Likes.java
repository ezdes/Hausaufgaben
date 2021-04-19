package entity;
import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "likes")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
