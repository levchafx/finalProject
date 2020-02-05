package by.levchenko.service;

import by.levchenko.domain.Author;
import by.levchenko.domain.Book;
import by.levchenko.domain.BookInstance;
import by.levchenko.domain.User;
import by.levchenko.repository.AuthorRepository;
import by.levchenko.repository.BookInstanceRepository;
import by.levchenko.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;
    private UserService userService;
    private BookInstanceRepository bookInstanceRepository;
    private AuthorRepository authorRepository;
@Autowired
    public BookService(BookRepository bookRepository, UserService userService, BookInstanceRepository bookInstanceRepository,AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.bookInstanceRepository = bookInstanceRepository;
        this.authorRepository=authorRepository;
    }

    public Book findById(long id){
        return bookRepository.findById(id).orElse(new Book());
    }
    public List<Book> findAll(){
      return bookRepository.findAll();
    }
    public void saveBook(Book b){
      b.setAuthors(verifyAuthors(b.getAuthors()));
    bookRepository.save(b);
    }
    @Transactional
    public void deleteBook(long id){
        List<BookInstance> bookInstances = bookInstanceRepository.findByBookId(id);
        for(BookInstance bookInstance:bookInstances){
            User u=userService.findById(bookInstance.getUserId());
            u.getBookshelf().remove(bookInstance);
        }
        bookRepository.deleteById(id);
    }
    public void deleteBook(Book b){
deleteBook(b.getId());

    }
    @Transactional
    public void getBook(long userId,long bookId,int weeks){
User user=userService.findById(userId);
        Book b = findById(bookId);
        b.setQuantity(b.getQuantity() - 1);
        BookInstance bi = new BookInstance();
        LocalDate localDate = LocalDate.now();
        bi.setBookId(bookId);
        bi.setUserId(user.getId());
        bi.setDueDate(Date.valueOf(localDate.plusWeeks(weeks)));
        user.getBookshelf().add(bi);
    }
    @Transactional
    public void returnBook(long bookId){
        BookInstance bi = bookInstanceRepository.findById(bookId).get();
        User user = userService.findById(bi.getUserId());
        Book b = findById(bi.getBookId());
        b.setQuantity(b.getQuantity() + 1);
        user.getBookshelf().remove(bi);
    }
    public Set<Author> verifyAuthors(Set<Author> authors){
    for(Author a:authors){
        Author author=authorRepository.findByNameAndSurname(a.getName(),a.getSurname()).orElse(new Author());
        if(author.getId()>0){
            authors.remove(a);
            authors.add(author);
        }
    }
    return authors;
    }
}
