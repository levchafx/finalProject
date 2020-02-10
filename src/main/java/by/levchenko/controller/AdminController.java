package by.levchenko.controller;

import by.levchenko.domain.Author;
import by.levchenko.domain.Book;
import by.levchenko.domain.User;
import by.levchenko.repository.MessageRepository;
import by.levchenko.service.BookService;
import by.levchenko.service.UserService;
import by.levchenko.utils.StringUtils;
import by.levchenko.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/admin")

public class AdminController {
    private BookValidator bookValidator;
    private UserService userService;
    private MessageRepository messageRepository;
    private BookService bookService;

    @Autowired
    public AdminController(UserService userService, MessageRepository messageRepository, BookService bookService, BookValidator bookValidator) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @InitBinder("book")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(bookValidator);
    }


    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", userService.findAll());
        return mv;
    }

    @GetMapping("/users/{id}")
    public ModelAndView userDetails(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("user");
        User u = userService.findById(id);
        u.getBookshelf();
        mv.addObject("user", u);
        return mv;
    }

    @PostMapping("/lockUser")
    public String lockUser(@RequestParam("id") long id) {
        userService.lockUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/unlockUser")
    public String unlockUser(@RequestParam("id") long id) {
        userService.unlockUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/messages")
    public ModelAndView readMessages() {
        ModelAndView mv = new ModelAndView("messages");
        mv.addObject("messages", messageRepository.findAll());
        return mv;
    }

    @GetMapping("/addBook")
    public String addBook(Model model, @RequestParam(value = "bookId", required = false) Long id) {
        if (id != null) {
            model.addAttribute("book", bookService.findById(id));
            return "bookForm";
        }
        Book b = new Book();
        b.getAuthors().add(new Author());
        model.addAttribute("book", b);
        return "bookForm";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") @Validated Book book, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "bookForm";
        }
        book.setAuthors(StringUtils.getAuthors(bindingResult.getFieldValue("author").toString()));
        if (file != null) {
            book.setImage(file.getBytes());
        }
        bookService.saveBook(book);

        return "redirect:/books";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
