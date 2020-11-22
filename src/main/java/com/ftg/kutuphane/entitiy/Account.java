package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account", schema = "\"kutuphane_schema\"")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", unique = true)
    private UUID id;

    @Column(name = "name")
    @NotNull(message = "Name must not be null")
    @NotEmpty(message = "Please provide an Name")
    private String name;

    @Column(name = "surname")
    @NotNull(message = "Surname must not be null")
    @NotEmpty(message = "Please provide an Surname")
    private String surname;

    @Column(name = "mail")
    @NotNull(message = "Mail must not be null")
    @NotEmpty(message = "Please provide an Mail Address")
    private String mail;

    @Column(name = "user_name", unique = true)
    @NotNull(message = "userName must not be null")
    @NotEmpty(message = "Please provide an User Name")
    private String userName;

    @Column(name = "password")
    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Please provide your password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Account(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.surname = account.getSurname();
        this.mail = account.getMail();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.role = account.getRole();
    }
}
