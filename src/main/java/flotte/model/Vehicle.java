package flotte.model;

import lombok.*;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;
}
