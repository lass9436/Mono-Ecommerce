package mono.ecommerce.security;

import jakarta.servlet.http.HttpServletResponse;
import mono.ecommerce.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserDetailServiceImpl userDetailService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(UserDetailServiceImpl userDetailService, JwtTokenProvider jwtTokenProvider) {
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

}
