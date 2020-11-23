package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.Book;
import com.ftg.kutuphane.entitiy.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop5ByOrderByIdDesc();

    //void deleteBooksByAuthor(Author author);

    //void deleteBooksByPublisher(Publisher publisher);

    Book findById(UUID id);

    boolean existsBookByIsbn(String isbn);

    Book findByIsbn(String isbn);
}
