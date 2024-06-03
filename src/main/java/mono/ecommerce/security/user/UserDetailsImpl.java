package mono.ecommerce.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = new ArrayList<>();
        authorities.forEach(s -> this.authorities.add(new SimpleGrantedAuthority("ROLE_"+s)));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
