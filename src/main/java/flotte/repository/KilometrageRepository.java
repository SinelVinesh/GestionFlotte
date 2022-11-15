package flotte.repository;

import flotte.model.Kilometrage;
import flotte.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface KilometrageRepository extends JpaRepository<Kilometrage, Long> {
    Optional<Kilometrage> findByVehicleIdAndId(Long vehicleId, Long id);
    Optional<List<Kilometrage>> findByVehicleId(Long vehicleId);
    @Transactional
    void deleteByVehicleIdAndId(Long vehicleId,Long id);
}
