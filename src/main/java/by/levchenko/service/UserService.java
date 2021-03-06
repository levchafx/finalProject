package by.levchenko.service;

import by.levchenko.domain.BookInstance;
import by.levchenko.domain.Role;
import by.levchenko.domain.User;
import by.levchenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(s);
        if (u == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return u;
    }

    public boolean saveUser(User u) {
        if (u.getId() != 0) {
            User user=userRepository.findById(u.getId()).get();
            if(!u.getPassword().equals(user.getPassword())){
                u.getAuthenticate().setPassword(encoder.encode(u.getAuthenticate().getPassword()));
            }
            Set<BookInstance> bookInstances = findById(u.getId()).getBookshelf();
            u.setBookshelf(bookInstances);
            userRepository.save(u);
            return true;
        }

        u.getAuthenticate().setPassword(encoder.encode(u.getPassword()));

        userRepository.save(u);
        return true;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Transactional
    public void lockUser(long id) {
        User user = findById(id);
        user.setRole(Role.ROLE_LOCKED);

    }

    public boolean loginExists(String login) {
        return userRepository.existsByAuthenticateLogin(login);
    }

    @Transactional
    public void unlockUser(long id) {
        User user = findById(id);
        user.setRole(Role.ROLE_USER);
    }
}
