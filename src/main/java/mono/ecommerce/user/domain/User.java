package mono.ecommerce.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mono.ecommerce.security.SignRequest;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;
    private String role;
    private Long point;

    public User(SignRequest signRequest) {
        this.username = signRequest.getUsername();
        this.password = signRequest.getPassword();
        this.nickname = signRequest.getNickname();
        this.point = 0L;
        this.role = "USER";
    }
}
