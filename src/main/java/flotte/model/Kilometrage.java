package flotte.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kilometrage")
public class Kilometrage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "DATE", name= "kilometrage_date")
    private LocalDate date;

    @Column(name= "counter_start")
    private Long start;

    @Column(name= "counter_end")
    private Long end;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;
}
