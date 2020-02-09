package by.levchenko.controller;

import by.levchenko.exception.ResourceNotFoundException;
import by.levchenko.exception.SomethingWentTerriblyWrongException;
import by.levchenko.exception.WrongTypeOfMethodException;
import by.levchenko.exception.YouAreNotAllowedToBeHereException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
@Controller
public class BasicErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws SomethingWentTerriblyWrongException, ResourceNotFoundException, YouAreNotAllowedToBeHereException, WrongTypeOfMethodException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException("place you are looking for doesn't exist,at least here");

            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                throw new SomethingWentTerriblyWrongException("something went terribly wrong");
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {

                throw new YouAreNotAllowedToBeHereException("you are not allowed to be here");
            }
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {

                throw new WrongTypeOfMethodException("method you are trying to reach this place with is incorrect");
            }
            else {
                throw  new SomethingWentTerriblyWrongException("something went terribly wrong");
            }
        }

    }
}
