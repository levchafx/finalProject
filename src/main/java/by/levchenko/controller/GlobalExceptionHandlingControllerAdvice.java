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

    @ExceptionHandler(SomethingWentTerriblyWrongException.class)
    public ModelAndView general(Exception e) {
        return prepareModelAndView(e);
    }

    @ExceptionHandler(YouAreNotAllowedToBeHereException.class)
    public ModelAndView forbidden(Exception e) {
        return prepareModelAndView(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView notFound(Exception e) {
        return prepareModelAndView(e);
    }

    @ExceptionHandler(WrongTypeOfMethodException.class)
    public ModelAndView wrongMethod(Exception e) {
        return prepareModelAndView(e);
    }

    private ModelAndView prepareModelAndView(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMessage", e.getMessage());
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }

}
