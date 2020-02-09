package by.levchenko.utils;

import by.levchenko.domain.Author;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StringUtils {
    private static final int NAME = 0;
    private static final int SURNAME = 1;

    public static Set<Author> getAuthors(String author) {

        Set<Author> authors = new HashSet<>();
        if (!author.contains(",")) {
            authors.add(getAuthor(author));
            return authors;
        }
        Arrays.stream(author.split(",")).forEach(e -> authors.add(getAuthor(e)));
        return authors;
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