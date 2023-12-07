package quote.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quote.demo.task.entity.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query(value = "SELECT * FROM quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote getByRandom();

    @Query(value = "SELECT * FROM quote q ORDER BY q.upvote DESC LIMIT 10", nativeQuery = true)
    List<Quote> getTopQuotes();

    @Query(value = "SELECT * FROM quote q ORDER BY q.downvote DESC LIMIT 10", nativeQuery = true)
    List<Quote> getWorseQuotes();

}
