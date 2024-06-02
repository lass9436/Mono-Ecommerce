package mono.ecommerce.security;

import jakarta.servlet.http.HttpServletResponse;
import mono.ecommerce.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserDetailServiceImpl userDetailService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailService = userDetailService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        final User user = userDetailService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        final String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        response.setHeader("Authorization", "Bearer " + token);
        return "Login successful";
    }

    @PostMapping("/sign")
    public String sign(@RequestBody SignRequest signRequest) {
        Optional<User> user = userDetailService.findByUsername(signRequest.getUsername());
        if(user.isPresent()) {throw new IllegalArgumentException("Already signed in");}
        userDetailService.sign(signRequest);
        return "Sign in successful";
    }

}
