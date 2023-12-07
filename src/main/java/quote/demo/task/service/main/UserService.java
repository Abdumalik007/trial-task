package quote.demo.task.service.main;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import quote.demo.task.dto.UserDto;

public interface UserService extends UserDetailsService {

    ResponseEntity<UserDto> createUser(UserDto userDto);
}
