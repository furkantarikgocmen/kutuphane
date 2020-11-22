package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop5ByOrderByIdDesc();

    void deleteBooksByAuthor(Author author);

    Book findById(UUID id);
}
