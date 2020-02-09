package by.levchenko.validator;

import by.levchenko.domain.Book;
import by.levchenko.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "book.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "author", "book.authors.empty");
        Book book =(Book)o;
        if(book.getQuantity()<=0){
            errors.rejectValue("quantity","book.quantity.error");
        }
if(!book.getAuthor().trim().contains(" ")){
    errors.rejectValue("author","author.name.incorrect");
}
    }
}
