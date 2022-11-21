package flotte.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle")
    @JsonManagedReference
    private List<Kilometrage> kilometrages;


}
