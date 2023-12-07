package quote.demo.task.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum Role {
    USER;


    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities
                = new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_".concat(this.name()))));
        return simpleGrantedAuthorities;
    }

}
