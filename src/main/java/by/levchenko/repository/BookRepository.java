package by.levchenko.repository;


import by.levchenko.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book.authors")
    Optional<Book> findById(long id);

    List<Book> findByTitleContainingIgnoreCase(String search);
}
