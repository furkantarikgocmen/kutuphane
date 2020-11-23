package com.ftg.kutuphane.service;


import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.entitiy.Publisher;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("publisherService")
public class PublisherService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherService.class);
    private final PublisherRepository publisherRepository;
    private final AccountService accountService;
    private final BookService bookService;

    public PublisherService(PublisherRepository publisherRepository, AccountService accountService, BookService bookService) {
        this.publisherRepository = publisherRepository;
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public long count() {
        return publisherRepository.count();
    }

    public List<Publisher> findTop5() {
        return publisherRepository.findTop5ByOrderByIdDesc();
    }

    public Publisher findById(UUID id) {
        return publisherRepository.findById(id);
    }

    public BackState save(Publisher publisher) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            try {
                publisherRepository.save(publisher);
                logger.info("Publisher {} Saved by {}_{}", publisher.getName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yeni Yayın Evi Ekleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Saving Publisher {} by {}_{} {}", publisher.getName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yayın Evi Ekleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Saving Publisher {} by {}_{}", publisher.getName(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yayın Evi Ekleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState update(Publisher publisher) {
        BackState backState = new BackState();
        if (accountService.isAdmin() || accountService.isModerator()) {
            try {
                publisher.setBooks(publisherRepository.findById(publisher.getId()).getBooks());
                publisherRepository.save(publisher);
                logger.info("Publisher {} Updated by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yayın Evi Güncelleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Updating Publisher {} by {}_{} {}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yayın Evi Güncelleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Updating Publisher {} by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yayın Evi Güncelleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }

    public BackState delete(Publisher publisher) {
        BackState backState = new BackState();
        if (accountService.isAdmin()) {
            try {
               /* if (bookService.deleteBooksByPublisher(publisher).getStateCode() == StateCode.SUCCESS.getStateCode()) {
                    publisherRepository.delete(publisher);
                    logger.info("Publisher {} Deleted by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                    backState.setMessage("Yayın Evi Silme İşlemi Başarılı!");
                    backState.setStateCode(StateCode.SUCCESS);
                } else {
                    logger.error("Error Deleting Publisher {} With Books by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                    backState.setMessage("Yayın Evini Kitapları İle Birlikte Silme İşleminde Bir Hata Oluştu!");
                    backState.setStateCode(StateCode.ERROR);
                }*/
                publisherRepository.delete(publisher);
                logger.info("Publisher {} Deleted by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yayın Evi Silme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Deleting Publisher {} by {}_{} {}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yayın Evi Silme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Deleting Publisher {} by {}_{}", publisher.getId(), accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yayın Evi Silme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
