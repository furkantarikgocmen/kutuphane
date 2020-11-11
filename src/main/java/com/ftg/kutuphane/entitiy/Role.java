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
@Table(name = "role", schema = "\"kutuphane_schema\"")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Role name must not be empty")
    private String name;
}
