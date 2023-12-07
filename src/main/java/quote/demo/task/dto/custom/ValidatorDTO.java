package quote.demo.task.dto.custom;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidatorDTO {
    private String fieldName;
    private String error;
}
