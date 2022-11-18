package flotte.services;

import flotte.model.Configuration;
import flotte.model.Token;
import flotte.model.User;
import flotte.repository.ConfigurationRepository;
import flotte.repository.TokenRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    public String generateToken(User user) {
        Optional<Configuration> configValue = configurationRepository.findByKey("token_duration");
        if(configValue.isPresent()) {
            Configuration config = configValue.get();
            long duration = Long.parseLong(config.getValue());
            LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);
            String tokenString = user.getPassword() + user.getUsername() + expiration.toString();
            String hash = DigestUtils.sha1Hex(tokenString);
            tokenRepository.deleteAllByUserId(user.getId());
            Token token = new Token();
            token.setUser(user);
            token.setToken(hash);
            token.setExpiration(expiration);
            tokenRepository.save(token);
            return hash;
        }
        return null;
    }

    public boolean authenticate(String token) {
        Optional<Token> data = tokenRepository.findByToken(token);
        if (data.isPresent()) {
            return data.get().getExpiration().isAfter(LocalDateTime.now());
        } else {
            return false;
        }
    }

    public boolean removeToken(String token) {
        try {
            tokenRepository.deleteByToken(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
