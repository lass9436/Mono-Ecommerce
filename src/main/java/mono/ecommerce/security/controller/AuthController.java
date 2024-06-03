package mono.ecommerce.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mono.ecommerce.security.user.UserDetailServiceImpl;
import mono.ecommerce.security.jwt.JwtTokenProvider;
import mono.ecommerce.security.redis.RedisService;
import mono.ecommerce.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        final User user = userDetailService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        final String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        response.setHeader("Authorization", "Bearer " + token);
        return "Login successful";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        log.info(token);
        redisService.addLogoutList(token);
        return "Logout successful";
    }

    @PostMapping("/sign")
    public String sign(@RequestBody SignRequest signRequest) {
        Optional<User> user = userDetailService.findByUsername(signRequest.getUsername());
        if(user.isPresent()) {throw new IllegalArgumentException("Already signed in");}
        userDetailService.sign(signRequest);
        return "Sign in successful";
    }

}
