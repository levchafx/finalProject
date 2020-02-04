package by.levchenko.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@NamedEntityGraph
        (name = "user.bookshelf",
        attributeNodes = @NamedAttributeNode("bookshelf"))
public class User implements UserDetails {
    public User(String name, Authenticate authenticate) {
        this.name = name;
        this.authenticate = authenticate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Authenticate authenticate;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ROLE_ADMIN','ROLE_USER','ROLE_GUEST')")
    private Role role;
    @ToString.Exclude
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
    private List<BookInstance> bookshelf;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getPassword() {
        return getAuthenticate().getPassword();
    }

    @Override
    public String getUsername() {
        return getAuthenticate().getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
