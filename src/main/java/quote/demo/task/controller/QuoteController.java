package quote.demo.task.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quote.demo.task.dto.QuoteDto;
import quote.demo.task.service.main.QuoteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteDto> createQuote(@RequestBody QuoteDto quoteDto) {
        return quoteService.createQuote(quoteDto);
    }

    @PutMapping
    public ResponseEntity<QuoteDto> updateQuote(@RequestBody QuoteDto quoteDto) {
        return quoteService.updateQuote(quoteDto);
    }

    @GetMapping("/get-by-random")
    public ResponseEntity<QuoteDto> getByRandom() {
        return quoteService.getByRandom();
    }


    @GetMapping("/top")
    public ResponseEntity<List<QuoteDto>> getTopQuotes() {
        return quoteService.getTopQuotes();
    }


    @GetMapping("/worse")
    public ResponseEntity<List<QuoteDto>> getWorseQuotes() {
        return quoteService.getWorseQuotes();
    }

    @PatchMapping("/upvote/{id}")
    public ResponseEntity<?> upvote(@PathVariable Integer id) {
        return quoteService.upvote(id);
    }

    @PatchMapping("/downvote/{id}")
    public ResponseEntity<?> downvote(@PathVariable Integer id) {
        return quoteService.downvote(id);
    }

}
