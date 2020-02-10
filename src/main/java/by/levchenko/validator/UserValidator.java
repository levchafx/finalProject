package by.levchenko.validator;

import by.levchenko.domain.User;
import by.levchenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "authenticate.password", "user.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "authenticate.login", "user.login.empty");
User u=(User)o;
if(userService.loginExists(u.getAuthenticate().getLogin())){
    errors.rejectValue("authenticate.login","user.login.exists");
}
if(!u.getAuthenticate().getPassword().equals(u.getAuthenticate().getConfirmPassword())){
    errors.rejectValue("authenticate.confirmPassword","user.password.match");
}
    }
}
