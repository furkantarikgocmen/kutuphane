package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop5ByOrderByIdDesc();

    void deleteBooksByAuthor(Author author);
}
