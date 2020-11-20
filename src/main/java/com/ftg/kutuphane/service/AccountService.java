package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.enums.RoleId;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("accountService")
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Account findAccountByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    public long count() {
        return accountRepository.count();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthorities() {
        return Arrays.toString(getAuthentication().getAuthorities().toArray());
    }

    public boolean isAdmin() {
        return getAuthorities().contains("ADMIN");
    }

    public boolean isModerator() {
        return getAuthorities().contains("MODERATOR");
    }

    public boolean isUser() {
        return getAuthorities().contains("USER");
    }

    public Account getActiveAccount() {
        return findAccountByUserName(getAuthentication().getName());
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated() && !(getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public boolean userNameExists(Account account) {
        return accountRepository.existsAccountByUserName(account.getUserName());
    }

    public Account findById(UUID id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public BackState newAccount(Account account) {
        BackState backState = new BackState();
        RoleId defaultRole = RoleId.USER;
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        if (userNameExists(account)) {
            logger.warn("Error Registration Account, Username Already Exists  {}", account.getUserName());
            backState.setMessage("Lütfen Farklı Bir Kullanıcı Adı Deneyin!");
            backState.setStateCode(StateCode.WARNING);
        } else if (isAuthenticated()) {
            if (isAdmin()) {
                try {
                    account.setRole(roleService.findById(account.getRole().getId()));
                    accountRepository.save(account);
                    logger.info("Account {} Registered by {}_{}", account.getUserName(), getActiveAccount().getUserName(), getAuthorities());
                    backState.setMessage("Yeni Kullanıcı Kayıt İşlemi Başarılı!");
                    backState.setStateCode(StateCode.SUCCESS);
                } catch (Exception e) {
                    logger.error("Error Registration Account {} {} by {}_{} {}", account.getName(), account.getSurname(), getActiveAccount().getUserName(), getAuthorities(), e.getMessage());
                    backState.setMessage("Yeni Kullanıcı Ekleme İşleminde Bir Hata Oluştu!");
                    backState.setStateCode(StateCode.ERROR);
                }
            } else if (isModerator()) {
                try {
                    account.setRole(roleService.findById(defaultRole.getRoleId()));
                    accountRepository.save(account);
                    logger.info("Account {} Registered by {}_{}", account.getUserName(), getActiveAccount().getUserName(), getAuthorities());
                    backState.setMessage("Yeni Kullanıcı Kayıt İşlemi Başarılı!");
                    backState.setStateCode(StateCode.SUCCESS);
                } catch (Exception e) {
                    logger.error("Error Registration Account {} {} by {}_{} {}", account.getName(), account.getSurname(), getActiveAccount().getUserName(), getAuthorities(), e.getMessage());
                    backState.setMessage("Yeni Kullanıcı Ekleme İşleminde Bir Hata Oluştu!");
                    backState.setStateCode(StateCode.ERROR);
                }
            } else {
                logger.warn("Access Denied Registration Account {} {} by {}_{}", account.getName(), account.getSurname(), getActiveAccount().getUserName(), getAuthorities());
                backState.setMessage("Kullanıcı Ekleme Yetkiniz Bulunmuyor!");
                backState.setStateCode(StateCode.WARNING);
            }
        } else {
            try {
                account.setRole(roleService.findById(defaultRole.getRoleId()));
                accountRepository.save(account);
                logger.info("Account {} Registered", account.getUserName());
                backState.setMessage("Kayıt İşleminiz Başarılı");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Registration Account {} {} {}", account.getName(), account.getSurname(), e.getMessage());
                backState.setMessage("Kayıt İşleminizde Bir Hata Oluştu");
                backState.setStateCode(StateCode.ERROR);
            }
        }
        return backState;
    }

    public BackState updateAccount(Account account) {
        BackState backState = new BackState();
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        if (!account.getPassword().equals("*****")) {
            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        } else {
            account.setPassword(accountRepository.findById(account.getId()).getPassword());
        }
        try {
            account.setRole(accountRepository.findById(account.getId()).getRole());
            account.setUserName(accountRepository.findById(account.getId()).getUserName());
            accountRepository.save(account);
            logger.info("Account {} Updated", account.getUserName());
            backState.setMessage("Güncelleme İşleminiz Başarılı");
            backState.setStateCode(StateCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Updating Account {} {}", account.getUserName(), e.getMessage());
            backState.setMessage("Güncelleme İşleminizde Bir Hata Oluştu");
            backState.setStateCode(StateCode.ERROR);
        }
        return backState;
    }

    public BackState delete(Account account) {
        BackState backState = new BackState();
        if (isAdmin()) {
            try {
                accountRepository.delete(account);
                logger.info("Account {} Deleted by {}_{}", account.getId(), getActiveAccount().getUserName(), getAuthorities());
                backState.setMessage("Kullanıcı Silme İşlemi Başarılı!");
                backState.setStateCode(StateCode.SUCCESS);
            } catch (Exception e) {
                logger.error("Error Deleting Account {} by {}_{} {}", account.getId(), getActiveAccount().getUserName(), getAuthorities(), e.getMessage());
                backState.setMessage("Kullanıcı Silme İşleminde Bir Hata Oluştu!");
                backState.setStateCode(StateCode.ERROR);
            }
        } else {
            logger.warn("Access Denied Deleting Account {} by {}_{}", account.getId(), getActiveAccount().getUserName(), getAuthorities());
            backState.setMessage("Kullanıcı Silme Yetkiniz Bulunmuyor!");
            backState.setStateCode(StateCode.WARNING);
        }
        return backState;
    }
}
