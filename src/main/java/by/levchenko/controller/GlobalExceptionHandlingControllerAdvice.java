package by.levchenko.controller;

import by.levchenko.exception.ResourceNotFoundException;
import by.levchenko.exception.SomethingWentTerriblyWrongException;
import by.levchenko.exception.WrongTypeOfMethodException;
import by.levchenko.exception.YouAreNotAllowedToBeHereException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice  {
    private static final String DEFAULT_ERROR_VIEW = "error";
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SomethingWentTerriblyWrongException.class)
    public ModelAndView general(Exception e) {
        return prepareModelAndView(e);
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(YouAreNotAllowedToBeHereException.class)
    public ModelAndView forbidden(Exception e) {
        return prepareModelAndView(e);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView notFound(Exception e) {
        return prepareModelAndView(e);
    }
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(WrongTypeOfMethodException.class)
    public ModelAndView wrongMethod(Exception e) {
      return prepareModelAndView(e);
    }
    private ModelAndView prepareModelAndView(Exception e){
    ModelAndView mv=new ModelAndView();
    mv.addObject("errorMessage",e.getMessage());
    mv.setViewName(DEFAULT_ERROR_VIEW);
    return mv;
}

}
