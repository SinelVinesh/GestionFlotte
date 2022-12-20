package flotte.repository;

import flotte.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
//    @Query("select new Vehicle(v.id,v.licensePlate) from Vehicle v")
//    List<Vehicle> findAllWithoutKilometrage();
}
