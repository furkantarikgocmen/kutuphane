package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.repository.AccountRepository;
import org.springframework.stereotype.Service;

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
}
