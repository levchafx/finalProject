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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {
private final int BOOK_QUANTITY=10;
   private UserService userService;
   private BookRepository bookRepository;
@Autowired
    public DataLoader(UserService userService, BookRepository bookRepository) {
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadData(){
        Faker faker=new Faker();
        User u= new User("sergei",new Authenticate("sergei","1234"));
        User user=new User("user",new Authenticate("user","1234"));
        userService.saveAdmin(u);
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
