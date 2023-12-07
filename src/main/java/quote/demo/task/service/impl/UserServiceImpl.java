package quote.demo.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quote.demo.task.dto.UserDto;
import quote.demo.task.entity.User;
import quote.demo.task.repository.UserRepository;
import quote.demo.task.security.Role;
import quote.demo.task.service.main.UserService;

import java.time.LocalDate;

import static quote.demo.task.helper.ResponseEntityHelper.INTERNAL_ERROR;
import static quote.demo.task.helper.ResponseEntityHelper.OK_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        try {
            User user = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(encoder.encode(userDto.getPassword()))
                    .role(Role.USER)
                    .createdAt(LocalDate.now()).build();
            userRepository.save(user);

            userDto.setCreatedAt(user.getCreatedAt());
            userRepository.save(user);

            return OK_MESSAGE(userDto);
        }catch (Exception e) {
            logger.error("Error while creating user!");
            return INTERNAL_ERROR(null);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow();
    }
}
