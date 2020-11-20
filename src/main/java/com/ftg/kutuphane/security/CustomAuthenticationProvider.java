package com.ftg.kutuphane.security;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private final AccountService accountService;

    public CustomAuthenticationProvider(AccountService accountService) {
        super();
        this.accountService = accountService;
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
            logger.error("Error loading user : " + userName + e.getMessage(), e);
        }

        if (account == null) {
            logger.warn("No account with this User Name " + userName);
            throw new UsernameNotFoundException("Kullanıcı Adı veya Şifre hatalı. Lütfen tekrar deneyiniz.");
        }

        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            logger.warn("Password is wrong for user " + account.getUserName() + " with id " + account.getId());
            throw new BadCredentialsException("Kullanıcı Adı veya Şifre hatalı. Lütfen tekrar deneyiniz.");
        }

        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(account.getRole().getName()));
        final UserDetails principal = new User(userName, password, grantedAuths);
        logger.info("Logged in user " + account.getUserName() + " with id " + account.getId());
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
