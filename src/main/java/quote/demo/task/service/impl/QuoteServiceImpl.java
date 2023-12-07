package quote.demo.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quote.demo.task.dto.QuoteDto;
import quote.demo.task.entity.Quote;
import quote.demo.task.entity.User;
import quote.demo.task.mapper.QuoteMapper;
import quote.demo.task.repository.QuoteRepository;
import quote.demo.task.service.main.QuoteService;

import java.time.LocalDate;
import java.util.List;

import static quote.demo.task.helper.ResponseEntityHelper.*;


@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    public static final Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);
    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;

    @Override
    public ResponseEntity<QuoteDto> createQuote(QuoteDto quoteDto) {
        try {
            Quote quote = Quote.builder()
                    .content(quoteDto.getContent())
                    .user(User.builder().id(quoteDto.getUser().getId()).build())
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .downvote(0)
                    .upvote(0)
                    .build();

            quote = quoteRepository.save(quote);
            quoteDto = quoteMapper.toDto(quote);

            return OK_MESSAGE(quoteDto);
        }catch (Exception e) {
            logger.error("Error while creating a quote!");
            return INTERNAL_ERROR(null);
        }
    }


    @Override
    public ResponseEntity<QuoteDto> updateQuote(QuoteDto quoteDto) {
        try {

            Quote quote = quoteRepository.findById(quoteDto.getId()).orElseThrow();
            quote.setUpdatedAt(LocalDate.now());
            quote.setContent(quote.getContent());

            quote = quoteRepository.save(quote);

            quoteDto = quoteMapper.toDto(quote);

            return OK_MESSAGE(quoteDto);
        }catch (Exception e) {
            logger.error("Error while editing a quote!");
            return INTERNAL_ERROR(null);
        }
    }

    @Override
    public ResponseEntity<QuoteDto> getByRandom() {
        QuoteDto quoteDto = quoteMapper.toDto(quoteRepository.getByRandom());
        return OK_MESSAGE(quoteDto);
    }

    @Override
    public ResponseEntity<?> upvote(Integer id) {
        if(!quoteRepository.existsById(id))
            return BAD_REQUEST("Quote with this id is not found!");

        Quote quote = quoteRepository.findById(id).orElseThrow();
        quote.setUpvote(quote.getUpvote()+1);
        quoteRepository.save(quote);

        return OK_MESSAGE("ok");
    }

    @Override
    public ResponseEntity<?> downvote(Integer id) {
        if(!quoteRepository.existsById(id))
            return BAD_REQUEST("Quote with this id is not found!");

        Quote quote = quoteRepository.findById(id).orElseThrow();
        quote.setDownvote(quote.getDownvote()+1);
        quoteRepository.save(quote);

        return OK_MESSAGE("ok");
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getTopQuotes() {
        List<QuoteDto> quotes = quoteRepository.getTopQuotes()
                .stream().map(quoteMapper::toDto)
                .toList();
        return OK_MESSAGE(quotes);
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getWorseQuotes() {
        List<QuoteDto> quotes = quoteRepository.getWorseQuotes()
                .stream().map(quoteMapper::toDto)
                .toList();
        return OK_MESSAGE(quotes);
    }
}
