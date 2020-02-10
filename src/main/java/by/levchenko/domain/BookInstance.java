package by.levchenko.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class BookInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long bookId;
    private long userId;
    private Date dueDate;
}
