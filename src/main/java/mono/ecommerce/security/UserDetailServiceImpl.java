package mono.ecommerce.security;

import mono.ecommerce.user.domain.User;
import mono.ecommerce.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), List.of(user.getRole()));
    }

    public User findByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return passwordEncoder.matches(password, user.getPassword()) ? user : null;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void sign(SignRequest signRequest) {
        String password = passwordEncoder.encode(signRequest.getPassword());
        signRequest.setPassword(password);
        final User user = new User(signRequest);
        userRepository.save(user);
    }
}
