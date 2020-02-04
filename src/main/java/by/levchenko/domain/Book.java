package by.levchenko.domain;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@NamedEntityGraph(name="book.authors",attributeNodes ={ @NamedAttributeNode("authors"),@NamedAttributeNode("image")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte [] image;
    private String title;
    private String description;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable
    private Set<Author> authors =new HashSet<>();
    private int quantity;

    public Book(String title,String description,Set<Author> authors,int quantity) {
        this.authors=authors;
        this.description=description;
        this.title=title;
        this.quantity=quantity;
    }
}
