package by.levchenko.controller;

import by.levchenko.exception.ResourceNotFoundException;
import by.levchenko.exception.SomethingWentTerriblyWrongException;
import by.levchenko.exception.WrongTypeOfMethodException;
import by.levchenko.exception.YouAreNotAllowedToBeHereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller

public class BasicErrorController implements ErrorController {
    @Autowired
    private Environment env;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws SomethingWentTerriblyWrongException, ResourceNotFoundException, YouAreNotAllowedToBeHereException, WrongTypeOfMethodException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException(env.getProperty("exception.404"));

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                throw new SomethingWentTerriblyWrongException(env.getProperty("exception.500"));
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {

                throw new YouAreNotAllowedToBeHereException(env.getProperty("exception.403"));
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {

                throw new WrongTypeOfMethodException(env.getProperty("exception.405"));
            } else {
                throw new SomethingWentTerriblyWrongException(env.getProperty("exception.500"));
            }
        }

    }
}
