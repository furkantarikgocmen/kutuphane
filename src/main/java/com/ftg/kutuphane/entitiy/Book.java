package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book", schema = "\"kutuphane_schema\"")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotEmpty
    @NotNull
    @Column(name = "name")
    String name;

    //todo: regex
    @NotEmpty
    @NotNull
    @Column(name = "ısbn")
    String isbn;

    @Column(name = "subName")
    String subName;

    @Column(name = "seriesName")
    String seriesName;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinTable(
            name = "book_author",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Author author;

    @ManyToOne
    @JoinTable(name = "book_publisher",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Publisher publisher;
}
