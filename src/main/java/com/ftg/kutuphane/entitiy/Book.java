package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book", schema = "\"kutuphane_schema\"")
public class Book {

    @Column(name = "name")
    String name;
    //todo: regex
    @Column(name = "ısbn")
    String isbn;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @OneToOne
    @JoinTable(
            name = "book_author",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Author author;

    @OneToOne
    @JoinTable(name = "book_publisher",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Publisher publisher;

    //todo: subName, seriesName, description vs. props
}
