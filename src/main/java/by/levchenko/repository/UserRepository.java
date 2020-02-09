package by.levchenko.repository;

import by.levchenko.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.authenticate.login=:username")
    User findByUsername(String username);
   @EntityGraph(value = "user.bookshelf")
   Optional<User> findById(long id);
   boolean existsByAuthenticateLogin(String username);
}
