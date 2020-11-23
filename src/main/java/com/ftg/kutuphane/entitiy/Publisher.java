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
@Table(name = "publisher", schema = "\"kutuphane_schema\"")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide an Name")
    @NotNull(message = "Name must not be null")
    private String name;

    @Column(name = "info")
    @NotNull(message = "Name must not be null")
    private String info;

    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher",
            schema = "\"kutuphane_schema\"",
            joinColumns = @JoinColumn(name = "publisher_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    //todo: description, telephone, e-mail vs. props
}
