package flotte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Insurance {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;
    @Column(name="start_date",columnDefinition = "DATE")
    private LocalDate start;
    @Column(name="end_date",columnDefinition = "DATE")
    private LocalDate end;

}
