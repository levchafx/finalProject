package by.levchenko.controller;

import by.levchenko.domain.Book;
import by.levchenko.domain.User;
import by.levchenko.service.BookService;
import by.levchenko.service.UserService;
import by.levchenko.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/")
public class MainController {
    private UserValidator userValidator;
    private BookService bookService;
    private UserService userService;

    @Autowired
    public MainController(BookService bookService, UserService userService, UserValidator userValidator) {
        this.bookService = bookService;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String allBooks(Model model) {

        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable long id,Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {

        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {

            return "registration";
        }
        userService.saveUser(user);
        return "index";
    }
    @PostMapping("/search")
    public String search(Model model,@RequestParam String search){
        List<Book> books=bookService.searchByTitle(search);
        if(!books.isEmpty()) {
            model.addAttribute("books",books);
            return "books";
        }
        model.addAttribute("result","search returned no results");
        return "books";
    }

}
