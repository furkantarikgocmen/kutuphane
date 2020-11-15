package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    private String name;

    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", schema = "\"kutuphane_schema\"", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    //todo: Email, Telephone, name-lastName, active-inactive, birthday vs. props.
}