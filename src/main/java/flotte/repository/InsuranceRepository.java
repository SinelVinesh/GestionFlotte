package flotte.repository;

import flotte.model.Insurance;
import flotte.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    Optional<Insurance> findFirstByVehicleOrderByEndDesc(Vehicle vehicle);
}
