package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.entitiy.Book;
import com.ftg.kutuphane.entitiy.Publisher;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Book findById(UUID id) {
        return bookRepository.findById(id);
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public boolean existsBookByIsbn(String isbn) {
        return bookRepository.existsBookByIsbn(isbn);
    }

    public BackState save(Book book) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            if (existsBookByIsbn(book.getIsbn())) {
                logger.warn("Error Saving Book, ISBN Already Exists  {}", book.getIsbn());
                backState.setMessage("Bu ISBN Numarasına Kayıtlı Bir Kitap Zaten Var!");
                backState.setStateCode(StateCode.WARNING);
                return backState;
            }
            try {
                bookRepository.save(book);
                logger.info("Book {} {} Saved by {}_{}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yeni Kitap Ekleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Saving Book {} {} by {}_{} {}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Kitap Ekleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Saving Book {} {} by {}_{}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Kitap Ekleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState update(Book book) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            if (existsBookByIsbn(book.getIsbn())) {
                if(!bookRepository.findByIsbn(book.getIsbn()).getId().equals(book.getId())  && bookRepository.findByIsbn(book.getIsbn()).getIsbn().equals(book.getIsbn())){
                    logger.warn("Error Saving Book, ISBN Already Exists  {}", book.getIsbn());
                    backState.setMessage("Bu ISBN Numarasına Kayıtlı Bir Kitap Zaten Var!");
                    backState.setStateCode(StateCode.WARNING);
                    return backState;
                }
            }
            try {
                bookRepository.save(book);
                logger.info("Book {} {} Updated by {}_{}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Kitap Güncelleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Updating Book {} {} {}", book.getName(), book.getSubName(), e.getMessage());
                backState.setMessage("Güncelleme İşleminizde Bir Hata Oluştu");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Updating Book {} {} by {}_{}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Kitap Güncelleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState delete(Book book) {
        BackState backState = new BackState();
        if (accountService.isAdmin()) {
            try {
                bookRepository.delete(book);
                logger.info("Book {}_{} {} Deleted by {}_{}", book.getId(), book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Kitap Silme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Deleting Book {}_{} {}", book.getId(), book.getName(), e.getMessage());
                backState.setMessage("Silme İşleminizde Bir Hata Oluştu");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Deleting Book {} {} by {}_{}", book.getName(), book.getSubName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Kitap Silme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
