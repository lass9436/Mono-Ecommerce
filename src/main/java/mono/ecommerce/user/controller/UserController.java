package mono.ecommerce.user.controller;

import lombok.extern.slf4j.Slf4j;
import mono.ecommerce.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/temp")
    public String userTemp(){
        return "you are Authorize";
    }

    @PostMapping("/user/charge")
    public String userCharge(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserChargeRequest userChargeRequest){
        Long resultPoint = userService.userCharge(userDetails.getUsername(), userChargeRequest);
        return "charge : " + userChargeRequest.getPoint() + " result : " + resultPoint;
    }
}
