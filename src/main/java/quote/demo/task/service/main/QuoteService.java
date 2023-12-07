package quote.demo.task.service.main;

import org.springframework.http.ResponseEntity;
import quote.demo.task.dto.QuoteDto;

import java.util.List;

public interface QuoteService {

    ResponseEntity<QuoteDto> createQuote(QuoteDto quoteDto);

    ResponseEntity<QuoteDto> updateQuote(QuoteDto quoteDto);

    ResponseEntity<QuoteDto> getByRandom();

    ResponseEntity<?> upvote(Integer id);

    ResponseEntity<?> downvote(Integer id);

    ResponseEntity<List<QuoteDto>> getTopQuotes();

    ResponseEntity<List<QuoteDto>> getWorseQuotes();
}
