package by.levchenko.controller;

import by.levchenko.repository.BookRepository;
import by.levchenko.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/")
public class MainController {
    @Autowired
    BookService bookService;
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
}
