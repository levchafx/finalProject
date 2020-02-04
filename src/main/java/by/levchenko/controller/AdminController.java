package by.levchenko.controller;

import by.levchenko.domain.Book;
import by.levchenko.domain.Role;
import by.levchenko.domain.User;
import by.levchenko.repository.BookRepository;
import by.levchenko.repository.MessageRepository;
import by.levchenko.service.BookService;
import by.levchenko.service.UserService;
import by.levchenko.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/admin")

public class AdminController {

    private UserService userService;
    private MessageRepository messageRepository;
    private BookService bookService;
@Autowired
    public AdminController(UserService userService, MessageRepository messageRepository, BookService bookService) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.bookService = bookService;
    }

    @GetMapping("/admin")
    public String admin(){
        return "index";
    }
    @GetMapping("/users")
    public ModelAndView users(){
        ModelAndView mv= new ModelAndView("users");
        mv.addObject("users",userService.findAll());
        return mv;
    }

    @GetMapping("/users/{id}")
    public ModelAndView userDetails(@PathVariable long id){
        ModelAndView mv=new ModelAndView("user");
        User u =userService.findById(id);
        u.getBookshelf();
        mv.addObject("user",u);
        return mv;
    }
    @PostMapping("/lockUser")
    public String lockUser( @RequestParam("id") long id){
       userService.lockUser(id);
        return "redirect:/admin/users";
    }
    @PostMapping("/unlockUser")
    public String unlockUser( @RequestParam("id") long id){
        userService.unlockUser(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/messages")
    public ModelAndView readMessages(){
        ModelAndView mv=new ModelAndView("messages");
        mv.addObject("messages",messageRepository.findAll());
        return mv;
    }
    @GetMapping("/addBook")
    public String addBook(){
    return "bookForm";
    }
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult, @RequestParam("image") MultipartFile file ,HttpServletRequest request) throws IOException {
book.setAuthors(StringUtils.getAuthors(request.getParameter("authors")));
    book.setImage(file.getBytes());
        System.out.println(file.getBytes());
    bookService.saveBook(book);

        return"redirect:/books" ;
    }
}
