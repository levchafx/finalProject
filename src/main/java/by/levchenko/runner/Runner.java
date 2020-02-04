package by.levchenko.runner;

import by.levchenko.utils.StringUtils;

public class Runner {
    public static void main(String[] args) {
        String author="mikhail bulghakov,alexandr pushkin,ivan krylov";
        StringUtils.getAuthors(author).forEach(System.out::println);
    }
}
