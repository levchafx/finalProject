package by.levchenko.controller;

import by.levchenko.domain.Authenticate;
import by.levchenko.domain.User;
import by.levchenko.repository.BookRepository;
import by.levchenko.service.BookService;
import by.levchenko.service.UserService;
import by.levchenko.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller()
@RequestMapping("/")
public class MainController {
private UserValidator userValidator;
   private BookService bookService;
   private UserService userService;
    @Autowired
    public MainController(BookService bookService,UserService userService,UserValidator userValidator){
        this.bookService=bookService;
        this.userService=userService;
        this.userValidator=userValidator;
    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }
    @GetMapping("/home")
    public String home(){
        return "index";
    }
@GetMapping("/books")
    public ModelAndView allBooks(){
    System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ModelAndView mv=new ModelAndView("books");
        mv.addObject("books",bookService.findAll());
        return mv;
}
@GetMapping("/books/{id}")
    public ModelAndView bookDetails(@PathVariable long id){
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("book",bookService.findById(id));
        return mv;
}
    @GetMapping("/register")
    public String registrationForm(Model model){

    model.addAttribute("user",new User());
    return "registration";
}

    @PostMapping("/register")
    public String registration( @ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model){


          if(bindingResult.hasErrors()){

           return "registration";
       }
userService.saveUser(user);
       return "index";
    }
}
