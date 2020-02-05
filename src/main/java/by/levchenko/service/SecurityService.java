package by.levchenko.service;

import by.levchenko.domain.User;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityService {
    public static User returnPrincipal(){

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
