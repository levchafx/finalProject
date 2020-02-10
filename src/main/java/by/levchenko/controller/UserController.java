package by.levchenko.controller;


import by.levchenko.domain.BookInstance;
import by.levchenko.domain.Message;
import by.levchenko.domain.User;
import by.levchenko.repository.BookInstanceRepository;
import by.levchenko.repository.MessageRepository;
import by.levchenko.service.BookService;
import by.levchenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.levchenko.service.SecurityService.returnPrincipal;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private BookInstanceRepository bookInstanceRepository;
    private BookService bookService;
    private MessageRepository messageRepository;

    @Autowired
    public UserController(BookInstanceRepository bookInstanceRepository, BookService bookService, UserService userService, MessageRepository messageRepository) {
        this.bookInstanceRepository = bookInstanceRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.messageRepository = messageRepository;

    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/getBook")
    public String getBook(@RequestParam("book_id") long bookId, @RequestParam("weeks") int weeks,Model model) {
        bookService.getBook(returnPrincipal().getId(), bookId, weeks);
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/bookshelf")
    public String bookshelf(Model model) {

        List<BookInstance> bookInstanceList = bookInstanceRepository.findByUserId(returnPrincipal().getId());
        if (!bookInstanceList.isEmpty()) {
            model.addAttribute("books", bookInstanceList);
            return "bookshelf";
        }
       model.addAttribute("bookshelfempty", "bookshelf is empty");
        return "bookshelf";
    }


    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("book_id") long id, HttpServletRequest request) {
        bookService.returnBook(id);
        final String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(Model model) {

        model.addAttribute("message", new Message());
        return "messageForm";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute("message") Message message, BindingResult bindingResult) {
        message.setUserId(returnPrincipal().getId());
        messageRepository.save(message);
        return "index";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        User user = userService.findById(id);
        user.getAuthenticate().setConfirmPassword(user.getPassword());
        model.addAttribute("user", user);
        return "registration";
    }
    @GetMapping("/edit")
    public String editUser( Model model) {
        User user = userService.findById(returnPrincipal().getId());
        user.getAuthenticate().setConfirmPassword(user.getPassword());
        model.addAttribute("user", user);
        return "registration";
    }
}
