package com.ftg.kutuphane.entitiy;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Column(name = "account_id")
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide an Name")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Please provide an Surname")
    private String surname;

    @Column(name = "mail")
    @NotEmpty(message = "Please provide an Mail Address")
    private String mail;

    @Column(name = "user_name", unique = true)
    @NotEmpty(message = "Please provide an User Name")
    private String userName;

    @Column(name = "password")
    @NotEmpty(message = "Please provide your password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
