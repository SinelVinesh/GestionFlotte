package flotte.repository;

import flotte.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    @Transactional
    void deleteAllByUserId(Long userId);

    @Transactional
    void deleteByToken(String token);
}