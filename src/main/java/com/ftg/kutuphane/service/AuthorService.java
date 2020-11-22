package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("authorService")
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;
    private final AccountService accountService;
    private final BookService bookService;

    public AuthorService(AuthorRepository authorRepository, AccountService accountService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public long count() {
        return authorRepository.count();
    }

    public List<Author> findTop5() {
        return authorRepository.findTop5ByOrderByIdDesc();
    }

    public Author findById(UUID id) {
        return authorRepository.findById(id);
    }


    public BackState save(Author author) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            try {
                authorRepository.save(author);
                logger.info("Author {} {} Saved by {}_{}", author.getName(), author.getSurname(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yeni Yazar Ekleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Saving Author {} {} by {}_{} {}", author.getName(), author.getSurname(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yazar Ekleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Saving Author {} {} by {}_{}", author.getName(), author.getSurname(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yazar Ekleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState update(Author author) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            try {
                author.setBooks(authorRepository.findById(author.getId()).getBooks());
                authorRepository.save(author);
                logger.info("Author {} Updated by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yazar Güncelleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Updating Author {} by {}_{} {}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yazar Güncelleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Updating Author{} by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yazar Güncelleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState delete(Author author) {
        BackState backState = new BackState();
        if (accountService.isAdmin()) {
            try {
                if (bookService.deleteBooksByAuthor(author).getStateCode() == StateCode.SUCCESS.getStateCode()) {
                    authorRepository.delete(author);
                    logger.info("Author {} Deleted by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                    backState.setMessage("Yazar Silme İşlemi Başarılı!");
                    backState.setStateCode(StateCode.SUCCESS);
                } else {
                    logger.error("Error Deleting Author {} With Books by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                    backState.setMessage("Yazarı Kitapları İle Birlikte Silme İşleminde Bir Hata Oluştu!");
                    backState.setStateCode(StateCode.ERROR);
                }
            } catch (Exception e) {
                logger.error("Error Deleting Author {} by {}_{} {}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yazar Silme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Deleting Author {} by {}_{}", author.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yazar Silme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
