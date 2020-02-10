package by.levchenko.controller;

import by.levchenko.exception.ResourceNotFoundException;
import by.levchenko.exception.SomethingWentTerriblyWrongException;
import by.levchenko.exception.WrongTypeOfMethodException;
import by.levchenko.exception.YouAreNotAllowedToBeHereException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public ModelAndView general(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMessage", e.getMessage());
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }



}
