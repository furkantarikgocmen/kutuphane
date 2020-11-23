package com.ftg.kutuphane.entitiy;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserDetail extends Account implements UserDetails {

    private final UUID id;

    private final String name;

    private final String surname;

    private final String mail;

    private final String userName;

    private final String password;

    private final Role role;


    /**
     * Creates a new UserDetail object.
     *
     * @param account  Account
     * @param id       UUID
     * @param name     String
     * @param surname  String
     * @param mail     String
     * @param userName String
     * @param password String
     * @param role     Role
     */
    public UserDetail(Account account, UUID id, String name, String surname, String mail, String userName, String password, Role role) {
        super(account);
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

}
