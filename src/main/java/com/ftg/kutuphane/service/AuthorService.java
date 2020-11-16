package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("authorService")
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;
    private final AccountService accountService;

    public AuthorService(AuthorRepository authorRepository, AccountService accountService) {
        this.authorRepository = authorRepository;
        this.accountService = accountService;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public long count(){
        return authorRepository.count();
    }

    public List<Author> findTop5(){
        return authorRepository.findTop5ByOrderByIdDesc();
    }


    public BackState save(Author author){
        BackState backState = new BackState();
        if(accountService.isAdmin() || accountService.isModerator()){
            try{
                authorRepository.save(author);
                logger.info("Author {} {} Saved by {}_{}", author.getName(),author.getLastName(),accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
                backState.setMessage("Yeni Yazar Ekleme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            }catch (Exception e){
                logger.error("Error Saving Author {} {} by {}_{} {}", author.getName(),author.getLastName(),accountService.getActiveAccount().getUserName(), accountService.getAuthorities(), e.getMessage());
                backState.setMessage("Yazar Ekleme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        }else{
            logger.warn("Access Denied Saving Author{} {} by {}_{}", author.getName(),author.getLastName(),accountService.getActiveAccount().getUserName(), accountService.getAuthorities());
            backState.setMessage("Yazar Ekleme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
