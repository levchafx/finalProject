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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private BookInstanceRepository bookInstanceRepository;
    private BookService bookService;
    private MessageRepository messageRepository;
    private User u = null;

    @Autowired
    public UserController(BookInstanceRepository bookInstanceRepository, BookService bookService, UserService userService,MessageRepository messageRepository) {
        this.bookInstanceRepository = bookInstanceRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.messageRepository=messageRepository;

    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/getBook")
    public ModelAndView getBook(@RequestParam("book_id") long bookId, @RequestParam("weeks") int weeks) {

        u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(u.getId());
        bookService.getBook(user, bookId, weeks);
        ModelAndView mv = new ModelAndView("books");
        mv.addObject("books", bookService.findAll());
        return mv;
    }

    @GetMapping("/userCabinet")
    public String user() {
        return "index";
    }

    @GetMapping("/bookshelf")
    public ModelAndView bookshelf() {
        u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BookInstance> bookInstanceList = bookInstanceRepository.findByUserId(u.getId());
        ModelAndView mv = new ModelAndView("bookshelf");
        if (!bookInstanceList.isEmpty()) {
            mv.addObject("books", bookInstanceList);
            return mv;
        }
        mv.addObject("bookshelfempty", "bookshelf is empty");
        return mv;
    }


    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("book_id") long id, HttpServletRequest request) {
        bookService.returnBook(id);
        final String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    @GetMapping("/sendMessage")
   public ModelAndView sendMessage(){
        ModelAndView mv=new ModelAndView("messageForm");
        mv.addObject("message",new Message());
        return mv;
    }
    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute("message") Message message, BindingResult bindingResult){
        u=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setUserId(u.getId());
        messageRepository.save(message);
        return "redirect:/user/userCabinet";
    }
}
