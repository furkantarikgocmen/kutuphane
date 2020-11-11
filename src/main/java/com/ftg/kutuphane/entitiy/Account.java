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

    @Column(name = "user_name", unique = true)
    @NotEmpty(message = "Please provide an User Name")
    private String userName;

    @Column(name = "password")
    @NotEmpty(message = "Please provide your password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    //todo: Email, Telephone, name-lastName, active-inactive props.
}
