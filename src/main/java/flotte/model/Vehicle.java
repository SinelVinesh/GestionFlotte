package flotte.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "license_plate")
    private String licensePlate;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @JsonInclude(Include.NON_NULL)
    @OneToMany(mappedBy = "vehicle")
    @JsonManagedReference
    private List<Kilometrage> kilometrages;

    @Transient
    private Insurance currentInsurance;

    public Vehicle(long id,String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
    }
}
