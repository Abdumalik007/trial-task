package quote.demo.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuoteDto {
    private Integer id;
    @NotBlank(message = "Content must not be empty")
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private UserDto user;
    private Integer upvote;
    private Integer downvote;
}
