package by.levchenko.controller;

import by.levchenko.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/")
public class MainController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/home")
    public String home(){
        return "index";
    }
@GetMapping("/books")
    public ModelAndView allBooks(){
    System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ModelAndView mv=new ModelAndView("books");
        mv.addObject("books",bookRepository.findAll());
        return mv;
}
}
