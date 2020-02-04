package by.levchenko.repository;

import by.levchenko.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
   Optional<Author> findByNameAndSurname (String name, String surname);
}
