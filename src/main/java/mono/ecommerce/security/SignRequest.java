package mono.ecommerce.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequest {
    private String username;
    private String password;
    private String nickname;
}
