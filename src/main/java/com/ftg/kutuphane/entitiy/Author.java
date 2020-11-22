package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "author", schema = "\"kutuphane_schema\"")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide an Name")
    @NotNull(message = "Name must not be null")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Please provide an SurName")
    @NotNull(message = "Surname must not be null")
    private String surname;

    @Column(name = "description")
    @NotNull(message = "Description must not be null")
    private String description;

    @Singular
    @OneToMany(cascade = CascadeType.ALL) //try using set
    @JoinTable(name = "book_author",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;
}
