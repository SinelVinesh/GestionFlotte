package flotte.services;

import flotte.model.User;
import flotte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final TokenService tokenService;


    public UserService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String login(User user) {
        Optional<User> data = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return data.map(tokenService::generateToken).orElse(null);
    }

    public boolean logout(String token) {
        return tokenService.removeToken(token);
    }
}
