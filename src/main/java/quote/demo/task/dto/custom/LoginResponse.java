package quote.demo.task.dto.custom;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;
}
