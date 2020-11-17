package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.entitiy.Book;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final AccountService accountService;

    public BookService(BookRepository bookRepository, AccountService accountService) {
        this.bookRepository = bookRepository;
        this.accountService = accountService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public long count() {
        return bookRepository.count();
    }

    public List<Book> findTop5() {
        return bookRepository.findTop5ByOrderByIdDesc();
    }

    public BackState deleteBooksByAuthor(Author author) {
        BackState backState = new BackState();
        if (accountService.isAdmin()) {
            try {
                bookRepository.deleteBooksByAuthor(author);
                logger.info("Author {} Books Deleted by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yazar Kitaplarını Silme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Deleting Author {} Books by {}_{} {}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yazar Kitaplarını Silme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Deleting Author {} Books by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yazar Kitaplarını Silme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
