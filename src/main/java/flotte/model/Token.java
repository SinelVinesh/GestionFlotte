package flotte.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    @Column(columnDefinition = "DATE")
    private LocalDateTime expiration;
}