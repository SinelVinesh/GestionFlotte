package flotte.repository;

import flotte.model.Kilometrage;
import flotte.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KilometrageRepository extends JpaRepository<Kilometrage, Long> {
    Optional<Kilometrage> findByVehicleIdAndId(Long vehicleId, Long id);
}
