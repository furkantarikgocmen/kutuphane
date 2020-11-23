package com.ftg.kutuphane.security;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.MyUserDetailSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private final AccountService accountService;
    private final MyUserDetailSerivce myUserDetailSerivce;

    public CustomAuthenticationProvider(AccountService accountService, MyUserDetailSerivce myUserDetailSerivce) {
        super();
        this.accountService = accountService;
        this.myUserDetailSerivce = myUserDetailSerivce;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        final String userName = authentication.getName();
        final String password = authentication.getCredentials().toString();

        Account account = null;

        try {
            account = accountService.findAccountByUserName(userName);
        } catch (Exception e) {
            logger.error("Error Loading User : " + userName + e.getMessage(), e);
        }

        if (account == null) {
            logger.warn("No Account With This User Name " + userName);
            throw new UsernameNotFoundException("Kullanıcı Adı veya Şifre hatalı. Lütfen tekrar deneyiniz.");
        }

        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            logger.warn("Password Is Wrong For User " + account.getUserName() + " With Id " + account.getId());
            throw new BadCredentialsException("Kullanıcı Adı veya Şifre hatalı. Lütfen tekrar deneyiniz.");
        }

        final UserDetails principal = myUserDetailSerivce.loadUserByUsername(userName);
        logger.info("Logged In User " + account.getUserName() + " With Id " + account.getId());
        return new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
