package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {

        if (isRegistered(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Long max = maxId();
        user.setId(max == null ? 0 : max + 1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public boolean isRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public Long maxId() {
        return userRepository.maxId();
    }

}
