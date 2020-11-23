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

    @NotEmpty(message = "Please provide an Name")
    @NotNull(message = "Name must not be null")
    @Column(name = "name")
    String name;
    @NotEmpty(message = "Please provide an ISBN")
    @NotNull(message = "ISBN must not be null")
    @Column(name = "isbn", unique = true)
    String isbn;
    @Column(name = "sub_name")
    @NotNull(message = "SubName must not be null")
    String subName;
    @Column(name = "series_name")
    @NotNull(message = "SeriesName must not be null")
    String seriesName;
    @Column(name = "description")
    @NotNull(message = "Description must not be null")
    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
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
