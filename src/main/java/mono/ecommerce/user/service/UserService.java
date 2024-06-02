package mono.ecommerce.user.service;

import mono.ecommerce.user.controller.UserChargeRequest;
import mono.ecommerce.user.domain.User;
import mono.ecommerce.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long userCharge(String username, UserChargeRequest userChargeRequest) {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.charge(userChargeRequest.getPoint());
        return user.getPoint();
    }
}
