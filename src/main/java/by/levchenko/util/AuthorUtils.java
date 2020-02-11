package by.levchenko.util;

import by.levchenko.domain.Author;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorUtils {
    private static final int NAME = 0;
    private static final int SURNAME = 1;

    public static Set<Author> getAuthors(String author) {


        if (!author.contains(",")) {
            Set<Author> authors = new HashSet<>();
            authors.add(getAuthor(author));
            return authors;
        }

        return Arrays.stream(author.split(",")).map(String::trim).filter(s->!s.isEmpty()).map(AuthorUtils::getAuthor).collect(Collectors.toSet());
    }

    private static Author getAuthor(String author) {
        author.trim();
        String[] splitAuthor = author.split(" ");
        Author a = new Author();
        a.setName(splitAuthor[NAME]);
        a.setSurname(splitAuthor[SURNAME]);
        return a;
    }

}