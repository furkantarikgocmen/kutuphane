package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.repository.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("accountService")
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;

    public AccountService(AccountRepository accountRepository, RoleService roleService) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
    }

    public Account findAccountByUserName(String userName){
        return accountRepository.findByUserName(userName);
    }

    public long count(){
        return accountRepository.count();
    }

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthorities(){
        return Arrays.toString(getAuthentication().getAuthorities().toArray());
    }

    public boolean isAdmin(){
        return getAuthorities().contains("ADMIN");
    }

    public boolean isModerator(){
        return getAuthorities().contains("MODERATOR");
    }

    public boolean isUser(){
        return getAuthorities().contains("USER");
    }

    public Account getActiveAccount(){
        return findAccountByUserName(getAuthentication().getName());
    }
}
