package com.ftg.kutuphane.service;

import java.util.ArrayList;
import java.util.Set;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.entitiy.Role;
import com.ftg.kutuphane.entitiy.UserDetail;
import com.ftg.kutuphane.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
@Service("myUserDetailService")
public class MyUserDetailSerivce implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account      account    = accountRepository.findByUserName(username);
        return new UserDetail(account, account.getId(), account.getName(), account.getSurname(), account.getMail(), account.getUserName(), account.getPassword(), account.getRole());
    }
}
