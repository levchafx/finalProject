package by.levchenko.dataloader;

import by.levchenko.domain.Authenticate;
import by.levchenko.domain.Author;
import by.levchenko.domain.Book;
import by.levchenko.domain.User;
import by.levchenko.repository.BookRepository;
import by.levchenko.repository.UserRepository;
import by.levchenko.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component

public class DataLoader {
private final int BOOK_QUANTITY=10;
   private UserService userService;
   private BookRepository bookRepository;
   Environment environment;
@Autowired
    public DataLoader(UserService userService, BookRepository bookRepository,Environment environment) {
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.environment=environment;
    }

    @PostConstruct
    public void loadData(){
        Faker faker=new Faker();
        User admin= new User(environment.getProperty("admin.username"),new Authenticate(environment.getProperty("admin.username"), environment.getProperty("password")));
        User user=new User(environment.getProperty("user.username"),new Authenticate(environment.getProperty("user.username"), environment.getProperty("password")));
        userService.saveAdmin(admin);
        userService.saveUser(user);
for(int i=0;i<10;i++){
    Author a=new Author();
    a.setName(faker.name().firstName());
    a.setSurname(faker.name().lastName());
    Set<Author> authors=new HashSet<>();
    authors.add(a);
    Book book =new Book(faker.book().title().toString(),faker.book().genre().toString(),authors,BOOK_QUANTITY);
    bookRepository.save(book);
}

    }
}
