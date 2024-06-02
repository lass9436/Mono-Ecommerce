package mono.ecommerce.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/temp")
    public String userTemp(){
        return "you are Authorize";
    }
}
