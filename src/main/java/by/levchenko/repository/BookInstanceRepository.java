package by.levchenko.repository;

import by.levchenko.domain.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance,Long> {
List<BookInstance> findByUserId(long userId);
List<BookInstance> findByBookId(long bookId);
}
