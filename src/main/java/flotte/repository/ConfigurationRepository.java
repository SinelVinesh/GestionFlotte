package flotte.repository;

import flotte.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    public Optional<Configuration> findByKey(String key);
}
