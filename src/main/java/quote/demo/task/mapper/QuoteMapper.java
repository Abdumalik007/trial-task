package quote.demo.task.mapper;


import org.mapstruct.Mapper;
import quote.demo.task.dto.QuoteDto;
import quote.demo.task.entity.Quote;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    Quote toEntity(QuoteDto quoteDto);

    QuoteDto toDto(Quote quote);

}
