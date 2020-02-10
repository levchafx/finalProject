package by.levchenko.dataloader;

import by.levchenko.domain.*;
import by.levchenko.repository.BookRepository;
import by.levchenko.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component

public class DataLoader {
    private final int BOOK_QUANTITY = 10;
    private UserService userService;
    private BookRepository bookRepository;
    private Environment environment;

    @Autowired
    public DataLoader(UserService userService, BookRepository bookRepository, Environment environment) {
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.environment = environment;
    }

    @PostConstruct
    public void loadData() {
        Faker faker = new Faker();
        User admin = new User(environment.getProperty("admin.username"), new Authenticate(environment.getProperty("admin.username"), environment.getProperty("password"), environment.getProperty("password")), Role.ROLE_ADMIN);
        User user = new User(environment.getProperty("user.username"), new Authenticate(environment.getProperty("user.username"), environment.getProperty("password"), environment.getProperty("password")), Role.ROLE_USER);
        userService.saveUser(admin);
        userService.saveUser(user);
        for (int i = 0; i < 10; i++) {
            Author a = new Author();
            a.setName(faker.name().firstName());
            a.setSurname(faker.name().lastName());
            Set<Author> authors = new HashSet<>();
            authors.add(a);
            Book book = new Book(faker.book().title(), faker.book().genre(), authors, BOOK_QUANTITY);
            bookRepository.save(book);
        }

    }
}
