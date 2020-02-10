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
    public ModelAndView allBooks() {
        ModelAndView mv = new ModelAndView("books");
        mv.addObject("books", bookService.findAll());
        return mv;
    }

    @GetMapping("/books/{id}")
    public ModelAndView bookDetails(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("book", bookService.findById(id));
        return mv;
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
        List<Book> books=bookService.search(search);
        if(!books.isEmpty()) {
            model.addAttribute("books",books);
        }
        return "books";
    }

}
