package quote.demo.task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @ManyToOne
    private User user;
    private Integer upvote;
    private Integer downvote;
}
