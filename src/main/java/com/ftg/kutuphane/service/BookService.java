package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Book;
import com.ftg.kutuphane.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public long count(){
        return bookRepository.count();
    }

    public List<Book> findTop5(){
        return bookRepository.findTop5ByOrderByIdDesc();
    }
}
