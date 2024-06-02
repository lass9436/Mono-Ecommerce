package mono.ecommerce.security;

import mono.ecommerce.user.domain.User;
import mono.ecommerce.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), List.of(user.getRole()));
    }

    public User findByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
